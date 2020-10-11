package doubleslash05.mini.team11.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.category.CategoryAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class SearchFragment : BaseFragment() {
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = activity!!.toolbar_main.menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        supportActionBar!!.title = "1234"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        adapter.setData(
            listOf(
                MenuList.getSample(),
                MenuList.getSample()
            )
        )

        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = adapter
    }
}