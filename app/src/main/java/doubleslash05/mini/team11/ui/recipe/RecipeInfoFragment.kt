package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*
import kotlinx.android.synthetic.main.item_ingredient.view.*

class RecipeInfoFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        v.textview_recipeinfo_detail.text = "여유로운 주말에는 팬케이크 믹스를 활용하여 맛있고 간단한 브런치를 즐겨보세요! 재료도 간편하고 요리 과정도 쉽지만 분위기 내기에는 최고랍니다.기호에 따라 시럽 및 견과류 혹은 과일을 얹어 색다른 맛을 시도해 보세요!"

        // 주재료 동적
        for (i in 0 until 3){
            val item = inflater.inflate(R.layout.item_ingredient, v.layout_recipeinfo_ingredient_main, false)

            item.textview_ingredient_name.text = "Water"
            item.textview_ingredient_amount.text = "half cup"

            v.layout_recipeinfo_ingredient_main.addView(item)
        }

        // 부재료 동적
        for (i in 0 until 3){
            val item = inflater.inflate(R.layout.item_ingredient, v.layout_recipeinfo_ingredient_sub, false)

            item.textview_ingredient_name.text = "Water"
            item.textview_ingredient_amount.text = "half cup"

            v.layout_recipeinfo_ingredient_sub.addView(item)
        }

        // 영양정보
        return v
    }
}