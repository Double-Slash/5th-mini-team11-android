package doubleslash05.mini.team11.ui.main

import android.os.Bundle
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main2.*

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button_main_nav_search.setOnClickListener { goToSearch() }
        button_main_nav_favorite.setOnClickListener { goToFavorite() }
        button_main_nav_home.setOnClickListener { goToHome() }
    }


    private fun goToHome() {
        clearNavigation()
        button_main_nav_home.isSelected = true
    }

    private fun goToSearch() {
        clearNavigation()
        button_main_nav_search.isSelected = true
    }

    private fun goToFavorite() {
        clearNavigation()
        button_main_nav_favorite.isSelected = true
    }

    private fun clearNavigation() {
        button_main_nav_home.isSelected = false
        button_main_nav_favorite.isSelected = false
        button_main_nav_search.isSelected = false
    }
}