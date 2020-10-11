package doubleslash05.mini.team11.ui.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import doubleslash05.mini.team11.App
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.model.data.RecipeVideoData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.common.widget.recipevideo.RecipeVideoView
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    private val infoFragment = RecipeInfoFragment()
    private val stepFragment = RecipeStepFragment()
    private val menuId by lazy { intent.getIntExtra(EXTRA_MENU_ID, -1) }
    private var data: RecipeData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        checkbox_recipe_favorite.setOnClickListener {
            val data = data ?: return@setOnClickListener
            it as CheckBox
            RecipeModel.setFavorite(data.id, it.isChecked)
        }

        videoview_recipe.setOnChangeSectionListener(object : RecipeVideoView.OnChangeSectionListener {
            override fun onChangeSection(index: Int) {
                stepFragment.setStep(index)
            }
        })

        tablayout_recipe.addOnTabSelectedListener(this)

        if (App.prefs.isShowVoiceGuide)
            GuideVoiceDialog().show(supportFragmentManager, null)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_recipe, stepFragment)
            .add(R.id.fragment_recipe, infoFragment)
            .commit()

        RecipeModel.getRecipeDetail(menuId).observe(this, Observer {
            when (it) {
                is ApiStatus.Success -> {
                    data = it.data
                    refresh()
                }
            }
        })
    }

    @Override
    override fun onResume() {
        super.onResume()
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val transaction = supportFragmentManager.beginTransaction()
        when (tab.position) {
            0 -> transaction.show(infoFragment).hide(stepFragment)
            1 -> transaction.hide(infoFragment).show(stepFragment)
        }
        transaction.commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    @SuppressLint("SetTextI18n")
    fun refresh() {
        val data = data ?: return
        val recipeVideoData = RecipeVideoData(data.videoUrl, data.ms)
        videoview_recipe.setData(recipeVideoData)

        textview_recipe_main.text = data.name
        textview_recipe_sub.text = data.descriptionShort
        textview_recipe_time.text = "${data.cookingTime}분"
        textview_recipe_level.text = data.level

        checkbox_recipe_favorite.isChecked = data.favorite

        infoFragment.setData(data)
        stepFragment.setData(data)
    }

    companion object {
        const val EXTRA_MENU_ID = "EXTRA_MENU_ID"
        const val EXTRA_MENU_DATA = "EXTRA_MENU_DATA"
    }
}