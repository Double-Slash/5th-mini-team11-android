package doubleslash05.mini.team11.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import kotlinx.android.synthetic.main.item_recipe_step.view.*
import kotlinx.android.synthetic.main.item_recipe_step_detail.view.*
import kotlin.math.max

class RecipeStepAdapter() : RecyclerView.Adapter<RecipeStepAdapter.StepViewHolder>() {
    private var list: List<List<String>>? = null
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

        val instructions = list!![position]

        for (i in 0 until max(instructions.size, holder.numList.size)) {
            if (i >= instructions.size) {
                // TextView 가리기
                holder.instructionParent[i].visibility = View.GONE
                continue // Instruction 보다 View 가 많은 상황
            }

            if (i >= holder.instructionParent.childCount) {
                // TextView 생성
                holder.createInstruction()
            }

            holder.instructionParent.visibility = View.VISIBLE
            holder.numList[i].text = (i + 1).toString()
            holder.instructionList[i].text = instructions[i]

            holder.numList[i].isSelected = position <= currentStep
        }
    }

    fun setData(list: List<List<String>>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setStep(index: Int) {
        currentStep = index
        notifyDataSetChanged()
    }

    inner class StepViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val step = v.textview_step!!
        val instructionParent = v.layout_step_instruction!!
        val numList = ArrayList<TextView>()
        val instructionList = ArrayList<TextView>()

        fun createInstruction() {
            val v = LayoutInflater.from(instructionParent.context).inflate(R.layout.item_recipe_step_detail, instructionParent, false)
            numList.add(v.textview_stepdetail_num)
            instructionList.add(v.textview_stepdetail_instruction)
            instructionParent.addView(v)
        }
    }
}