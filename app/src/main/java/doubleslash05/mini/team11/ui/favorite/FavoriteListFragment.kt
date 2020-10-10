package doubleslash05.mini.team11.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.common.MenuDataAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class FavoriteListFragment private constructor() : BaseFragment() {
    private val adapter by lazy { MenuDataAdapter() }
    private val type by lazy { arguments!!.getInt(ARGUMENT_FAVORITE_TYPE) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = adapter

        val liveData = if (type == 0){
            RecipeModel.getFavoritesList()
        }else{
            RecipeModel.getMadeMenuList()
        }

        liveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiStatus.Success -> adapter.setData(it.data.menuList)
            }
        })
    }

    companion object {
        fun getInstance(type: Int): FavoriteListFragment {
            return FavoriteListFragment().apply {
                arguments = Bundle().apply { putInt(ARGUMENT_FAVORITE_TYPE, type) }
            }
        }

        const val ARGUMENT_FAVORITE_TYPE = "ARGUMENT_FAVORITE_TYPE"
    }
}