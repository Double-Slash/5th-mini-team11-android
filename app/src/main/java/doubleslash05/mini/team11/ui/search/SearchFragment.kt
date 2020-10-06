package doubleslash05.mini.team11.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.category.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class SearchFragment : BaseFragment() {
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setData(listOf(
            MenuList.getSample(),
            MenuList.getSample()
        ))

        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = adapter
    }
}