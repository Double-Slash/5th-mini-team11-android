package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*
import kotlinx.android.synthetic.main.item_ingredient.view.*

class RecipeInfoFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        return v
    }

    fun setData(data: RecipeData) {
        view?.textview_recipeinfo_detail?.text = data.descriptionLong

        val inflater = LayoutInflater.from(context)
        // 주재료 동적
        for (column in data.ingredient.main.entries) {
            val item = inflater.inflate(R.layout.item_ingredient, view?.layout_recipeinfo_ingredient_main, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            view?.layout_recipeinfo_ingredient_main?.addView(item)
        }

        // 부재료 동적
        for (column in data.ingredient.sub.entries) {
            val item = inflater.inflate(R.layout.item_ingredient, view?.layout_recipeinfo_ingredient_sub, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            view?.layout_recipeinfo_ingredient_sub?.addView(item)
        }

        // 영양정보
        for (column in data.nutrition.info.entries){
            val item = inflater.inflate(R.layout.item_ingredient, view?.layout_recipeinfo_ingredient_sub, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            view?.layout_recipeinfo_nutrition?.addView(item)
        }

        //칼로리

        val item = inflater.inflate(R.layout.item_ingredient, view?.layout_recipeinfo_ingredient_sub, false)

        item.textview_ingredient_name.text = "칼로리"
        item.textview_ingredient_amount.text = data.nutrition.calorie.toString()

        view?.layout_recipeinfo_nutrition?.addView(item)
    }
}