package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.model.data.RecipeStepData
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class RecipeStepFragment : BaseFragment() {
    private val adapter by lazy { RecipeStepAdapter() }
    private val smoothScroller: RecyclerView.SmoothScroller by lazy {
        object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference() = SNAP_TO_START
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        v.recyclerview.layoutManager = LinearLayoutManager(context)
        v.recyclerview.adapter = adapter

        return v
    }

    fun setData(data : RecipeData) {
        adapter.setData(data.step)
    }

    fun setStep(index: Int) {
        smoothScroller.targetPosition = index
        view?.recyclerview?.layoutManager?.startSmoothScroll(smoothScroller)
        adapter.setStep(index)
    }
}