package doubleslash05.mini.team11.ui.category

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    private val fragmentList = ArrayList<CategoryFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val strArray = arrayOf(
            getString(R.string.category_1),
            getString(R.string.category_2),
            getString(R.string.category_3),
            getString(R.string.category_4),
            getString(R.string.category_5),
            getString(R.string.category_6),
            getString(R.string.category_7),
            getString(R.string.category_8),
            getString(R.string.category_9)
        )


        for (str in strArray.indices) {
            val item = tablayout_category.newTab()
            tablayout_category.addTab(item)

            item.text = strArray[str]

            val bundle = Bundle().apply {
                putString(CategoryFragment.ARGUMENT_CATEGORY, strArray[str])
            }

            fragmentList.add(CategoryFragment().apply {
                arguments = bundle
            })

        }

        tablayout_category.addOnTabSelectedListener(this)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_category, fragmentList[0]).commit()

    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_category, fragmentList[tab.position]).commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
    }

}