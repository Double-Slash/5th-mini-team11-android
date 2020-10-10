package doubleslash05.mini.team11.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.model.data.RecipeStepData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe_step.view.*

class RecipeStepFragment : BaseFragment() {
    private lateinit var data: RecipeData
    private val adapter by lazy { RecipeStepAdapter() }
    private val smoothScroller: RecyclerView.SmoothScroller by lazy {
        object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference() = SNAP_TO_START
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerview_recipe.layoutManager = LinearLayoutManager(context)
        view.recyclerview_recipe.adapter = adapter

        view.button_recipe_make.setOnClickListener {
            if(data.isMade) return@setOnClickListener
            RecipeModel.setMade(data.id).observe(viewLifecycleOwner, Observer {
                if(it !is ApiStatus.Success) return@Observer
                data.isMade = true
                view?.button_recipe_make?.isSelected = data.isMade
            })
        }
    }

    fun setData(data : RecipeData) {
        this.data = data

        adapter.setData(data.step)
        view?.button_recipe_make?.isSelected = data.isMade
    }

    fun setStep(index: Int) {
        smoothScroller.targetPosition = index
        view?.recyclerview_recipe?.layoutManager?.startSmoothScroll(smoothScroller)
        adapter.setStep(index)
    }
}