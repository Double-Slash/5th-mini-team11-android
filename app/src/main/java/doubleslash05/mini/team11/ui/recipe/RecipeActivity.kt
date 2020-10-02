package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeVideoData
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.common.widget.recipevideo.RecipeVideoView
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    private val infoFragment = RecipeInfoFragment()
    private val stepFragment = RecipeStepFragment()
    private val menuId by lazy { intent.getIntExtra(EXTRA_MENU_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        //        textview_receipe_main.text = menuData.name
        //        textview_receipe_sub.text = menuData.descriptionShort

        videoview_receipe.setOnChangeSectionListener(object : RecipeVideoView.OnChangeSectionListener {
            override fun onChangeSection(index: Int) {
                stepFragment.setStep(index)
            }
        })
        tablayout_recipe.addOnTabSelectedListener(this)


        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_recipe, stepFragment)
            .add(R.id.fragment_recipe, infoFragment)
            .commit()
    }

    @Override
    override fun onResume() {
        super.onResume()
        val data = RecipeVideoData("https://doubleslash-test.s3.ap-northeast-2.amazonaws.com/out.mp4", arrayOf(30000, 60000, 90000, 120000))

        videoview_receipe.setData(data)
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

    companion object {
        const val EXTRA_MENU_ID = "EXTRA_MENU_ID"
        const val EXTRA_MENU_DATA = "EXTRA_MENU_DATA"
    }
}