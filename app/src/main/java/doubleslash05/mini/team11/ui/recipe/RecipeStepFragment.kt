package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeStepData
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class RecipeStepFragment : BaseFragment() {
    private val adapter by lazy { RecipeStepAdapter(context!!) }
    val smoothScroller: RecyclerView.SmoothScroller by lazy {
        object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference() = SNAP_TO_START
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        v.recyclerview.layoutManager = LinearLayoutManager(context)
        v.recyclerview.adapter = adapter

        adapter.setData(
            listOf(
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.", "멍울이 지지않게 풀어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.", "멍울이 지지않게 풀어줍니다.")),
                RecipeStepData(listOf("믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다.", "믹스와 물을 잘 섞어줍니다.", "반죽에 계란을 넣고 섞습니다.", "멍울이 지지않게 풀어줍니다."))
            )
        )
        return v
    }


    fun setStep(index: Int) {
        smoothScroller.targetPosition = index
        view?.recyclerview?.layoutManager?.startSmoothScroll(smoothScroller)
        adapter.setStep(index)
    }
}