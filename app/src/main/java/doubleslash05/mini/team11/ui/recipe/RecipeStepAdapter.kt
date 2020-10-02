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

class RecipeStepAdapter(private val context: Context) : RecyclerView.Adapter<RecipeStepAdapter.StepViewHolder>() {
    private var list: List<RecipeStepData>? = null
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val v = inflater.inflate(R.layout.item_recipe_step, parent, false)

        return StepViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.step.text = "STEP ${position + 1}"
        if (position < 2) holder.step.isSelected = true

        val instructions = list!![position].instructions

        for (i in 0 until max(instructions.size, holder.instruction.childCount)) {
            if (i >= holder.instruction.childCount) {
                // TextView 생성
                holder.instruction.addView(inflater.inflate(R.layout.item_recipe_step_detail, holder.instruction, false))
            } else if (i >= instructions.size) {
                // TextView 가리기
                holder.instruction[i].visibility = View.GONE
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

    inner class StepViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val step = v.textview_step!!
        val instruction = v.layout_step_instruction!!
    }
}