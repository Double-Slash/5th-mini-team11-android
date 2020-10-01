package doubleslash05.mini.team11.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class CategoryFragment : BaseFragment() {
    private val category: String by lazy { arguments!!.getString(ARGUMENT_CATEGORY, "all") }
    private val adapter by lazy { CategoryAdapter(context!!) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        v.recyclerview.layoutManager = LinearLayoutManager(context)
        v.recyclerview.adapter = adapter


        adapter.setData(listOf(
            MenuList.getSample(),
            MenuList.getSample()
        ))

        return v
    }

    companion object {
        const val ARGUMENT_CATEGORY = "category"
    }
}