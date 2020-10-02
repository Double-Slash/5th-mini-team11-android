package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeVideoData
import doubleslash05.mini.team11.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    private val ingredientFragment = IngredientFragment()
    private val stepFragment = RecipeStepFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        tablayout_recipe.addOnTabSelectedListener(this)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_recipe, ingredientFragment).commit()
    }

    @Override
    override fun onResume() {
        super.onResume()
        val data = RecipeVideoData("https://doubleslash-test.s3.ap-northeast-2.amazonaws.com/out.mp4", arrayOf(30000, 60000, 90000, 120000))

        videoview_receipe.setData(data)
    }

    override fun onTabSelected(tab: TabLayout.Tab) {

        val fragment = when(tab.position){
            0 -> ingredientFragment
            1 -> stepFragment
            else -> stepFragment
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_recipe, fragment).commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

}