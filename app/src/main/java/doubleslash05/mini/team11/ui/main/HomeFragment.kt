package doubleslash05.mini.team11.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.category.CategoryActivity
import doubleslash05.mini.team11.ui.category.CategoryActivity.Companion.EXTRA_CATEGORY_INDEX
import doubleslash05.mini.team11.ui.category.CategoryAdapter
import doubleslash05.mini.team11.ui.recipe.RecipeActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_title.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class HomeFragment : BaseFragment() {
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recyclerview_home.layoutManager = LinearLayoutManager(context)
        view.recyclerview_home.adapter = adapter
        activity?.textview_main_title?.text = getString(R.string.app_name).toUpperCase()
        activity?.textview_main_title?.setTextColor(ResourcesCompat.getColor(resources, R.color.orange_yellow, null))

        view.title_home.textview_title_main.text = "카테고리"
        view.title_home.textview_title_sub.text = "주재료를 선택해보세요!"

        val arr = arrayOf(
            view.imageview_home_category_0,
            view.imageview_home_category_1,
            view.imageview_home_category_2,
            view.imageview_home_category_3,
            view.imageview_home_category_4,
            view.imageview_home_category_5,
            view.imageview_home_category_6,
            view.imageview_home_category_7,
            view.imageview_home_category_8
        )

        val inflater = LayoutInflater.from(context)
        for (index in arr.indices) {
            arr[index].onClick {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(EXTRA_CATEGORY_INDEX, index)
                startActivity(intent)
            }
        }

        adapter.setOnMenuItemClickListener(object : CategoryAdapter.OnMenuItemClickListener {
            override fun onMenuItemClick(data: MenuData) {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra(RecipeActivity.EXTRA_MENU_ID, data.id)
                startActivity(intent)
            }
        })

        RecipeModel.getMain().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiStatus.Success -> {
                    adapter.setData(it.data.mainList)
                }
            }
        })
    }
}