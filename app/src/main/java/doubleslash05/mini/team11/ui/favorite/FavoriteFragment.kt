package doubleslash05.mini.team11.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewpager_favorite.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        view.viewpager_favorite.adapter = ViewPagerAdapter()

        TabLayoutMediator(view.tablayout_favorite, view.viewpager_favorite) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.favorite_make_menu)
                1 -> getString(R.string.favorite_made_menu)
                else -> "???"
            }
        }.attach()
    }


    inner class ViewPagerAdapter : FragmentStateAdapter(activity!!) {
        private val fragmentList = listOf(FavoriteListFragment(), FavoriteListFragment())

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}