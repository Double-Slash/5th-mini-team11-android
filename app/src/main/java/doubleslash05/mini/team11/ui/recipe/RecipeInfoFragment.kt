package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.util.extension.dpToPx
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*
import kotlinx.android.synthetic.main.item_ingredient.view.*

class RecipeInfoFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        return v
    }

    fun setData(data: RecipeData) {
        val view = view ?: return
        view.textview_recipeinfo_detail.text = data.descriptionLong
        view.textview_recipeinfo_tag_1.text = data.tags[0]
        view.textview_recipeinfo_tag_2.text = data.tags[1]
        view.textview_recipeinfo_tag_3.text = data.tags[2]
        view.textview_recipeinfo_servings.text = "${data.servings}인분 기준"


        val inflater = LayoutInflater.from(context)
        var list = data.ingredient.main.entries
        // 주재료 동적
        for (column in list) {
            val item = inflater.inflate(R.layout.item_ingredient, view.layout_recipeinfo_ingredient_main, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            if (list.last() == column)
                item.line_ingredient.visibility = View.GONE

            view.layout_recipeinfo_ingredient_main.addView(item)
        }

        list = data.ingredient.sub.entries
        // 부재료 동적
        for (column in list) {
            val item = inflater.inflate(R.layout.item_ingredient, view.layout_recipeinfo_ingredient_sub, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            if (list.last() == column)
                item.line_ingredient.visibility = View.GONE

            view.layout_recipeinfo_ingredient_sub.addView(item)
        }

        list = data.nutrition.info.entries
        // 영양정보
        for (column in data.nutrition.info.entries) {
            val item = inflater.inflate(R.layout.item_ingredient, view.layout_recipeinfo_ingredient_sub, false)

            item.textview_ingredient_name.text = column.key
            item.textview_ingredient_amount.text = column.value

            if (list.last() == column)
                item.line_ingredient.visibility = View.GONE

            view.layout_recipeinfo_nutrition.addView(item)
        }

        //칼로리

        val item = inflater.inflate(R.layout.item_ingredient, view.layout_recipeinfo_ingredient_sub, false)
        (item.layoutParams as LinearLayout.LayoutParams).topMargin = context!!.dpToPx(7)
        item.textview_ingredient_name.text = "칼로리"
        item.textview_ingredient_amount.text = "약 ${data.nutrition.calorie} kcal"
        item.line_ingredient.visibility = View.GONE

        view.layout_recipeinfo_nutrition.addView(item)
    }
}