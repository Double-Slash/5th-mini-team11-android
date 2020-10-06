package doubleslash05.mini.team11.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.category.CategoryAdapter
import doubleslash05.mini.team11.ui.common.MenuDataAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class FavoriteListFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = MenuDataAdapter().apply {
            setData(
                listOf(
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample()
                )
            )
        }

    }
}