package doubleslash05.mini.team11.ui.common

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.ui.recipe.RecipeActivity
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: List<MenuData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuDataViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is MenuDataViewHolder) return
        holder.bind(list!![position])
    }

    fun setData(list: List<MenuData>) {
        this.list = list
        notifyDataSetChanged()
    }

    private inner class MenuDataViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private lateinit var data: MenuData
        val background = v.imageview_menu_background!!
        private val txtMain = v.textview_menu_main!!
        private val txtSub = v.textview_menu_sub!!
        private val txtTime = v.textview_menu_time!!
        private val txtLevel = v.textview_menu_level!!
        private val checkbox = v.checkbox_menu_favorite!!

        init {
            v.setOnClickListener {
                val context = v.context
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra(RecipeActivity.EXTRA_MENU_ID, data.id)
                context.startActivity(intent)
            }

            checkbox.setOnClickListener { v ->
                v as CheckBox
                RecipeModel.setFavorite(data.id.toString(), v.isChecked)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: MenuData) {
            this.data = data
            Glide.with(background.context).load(data.thumbnailUrl).into(background)

            txtMain.text = data.name
            txtSub.text = data.descriptionShort
            txtTime.text = "${data.cookingTime} 분"
            txtLevel.text = data.level

            checkbox.isChecked = data.favorite
        }
    }
}