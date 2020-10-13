package doubleslash05.mini.team11.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.recipe.RecipeActivity
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class CategoryFragment private constructor(): BaseFragment() {
    private val category: String by lazy { arguments!!.getString(ARGUMENT_CATEGORY, "all") }
    private val adapter by lazy { CategoryAdapter() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = adapter


        adapter.setOnMenuItemClickListener(object : CategoryAdapter.OnMenuItemClickListener {
            override fun onMenuItemClick(data: MenuData) {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra(RecipeActivity.EXTRA_MENU_ID, data.id)
                startActivity(intent)
            }
        })

        RecipeModel.getCategory(category).observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiStatus.Success -> {
                    adapter.setData(it.data)
                }
            }
        })
    }

    companion object {
        fun getInstance(category: String): CategoryFragment {
            return CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_CATEGORY, category)
                }
            }
        }

        const val ARGUMENT_CATEGORY = "category"
    }
}