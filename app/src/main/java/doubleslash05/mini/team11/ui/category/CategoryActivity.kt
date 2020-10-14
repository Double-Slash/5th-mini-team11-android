package doubleslash05.mini.team11.ui.category

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    private val fragmentList = ArrayList<CategoryFragment>()
    private val startIndex by lazy { intent.getIntExtra(EXTRA_CATEGORY_INDEX, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setSupportActionBar(toolbar_category)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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


        for (str in strArray) {
            val item = tablayout_category.newTab()
            tablayout_category.addTab(item)
            with(item) {
                text = str

            }

            fragmentList.add(CategoryFragment.getInstance(str))
        }

        tablayout_category.addOnTabSelectedListener(this)
        Handler(Looper.myLooper()!!).postDelayed(
            { tablayout_category.getTabAt(startIndex)?.select() }, 100
        )

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        textview_category_title.text = tab.text
        supportFragmentManager.beginTransaction().replace(R.id.fragment_category, fragmentList[tab.position]).commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
    }

    companion object {
        const val EXTRA_CATEGORY_INDEX = "EXTRA_CATEGORY_INDEX"
    }
}