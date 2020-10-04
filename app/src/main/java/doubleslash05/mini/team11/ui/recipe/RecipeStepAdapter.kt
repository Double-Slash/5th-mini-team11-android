package doubleslash05.mini.team11.ui.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeStepData
import kotlinx.android.synthetic.main.item_recipe_step.view.*
import kotlin.math.max

class RecipeStepAdapter() : RecyclerView.Adapter<RecipeStepAdapter.StepViewHolder>() {
    private var list: List<RecipeStepData>? = null
    private var currentStep: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_step, parent, false)

        return StepViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.step.text = "STEP ${position + 1}"
        holder.step.isSelected = position <= currentStep

        val instructions = list!![position].instructions

        for (i in 0 until max(instructions.size, holder.instruction.childCount)) {
            if (i >= instructions.size) {
                // TextView 가리기
                holder.instruction[i].visibility = View.GONE
                continue // Instruction 보다 View 가 많은 상황
            }

            if (i >= holder.instruction.childCount) {
                // TextView 생성
                val v = LayoutInflater.from(holder.instruction.context).inflate(R.layout.item_recipe_step_detail, holder.instruction, false)
                holder.instruction.addView(v)
            }

            val txt = holder.instruction[i] as TextView
            txt.visibility = View.VISIBLE
            txt.text = "${i + 1}. ${instructions[i]}"
        }
    }

    fun setData(list: List<RecipeStepData>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setStep(index: Int){
        currentStep = index
        notifyDataSetChanged()
    }

    inner class StepViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val step = v.textview_step!!
        val instruction = v.layout_step_instruction!!
    }
}