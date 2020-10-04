package doubleslash05.mini.team11.ui.common

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.model.data.MenuData
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val txtMain = v.textview_menu_main!!
    val txtSub = v.textview_menu_sub!!
    val txtTime = v.textview_menu_time!!
    val txtLevel = v.textview_menu_level!!
    val checkbox = v.checkbox_menu_favorite!!

    private lateinit var data: MenuData
    private var onMenuItemClickListener: OnMenuItemClickListener? = null

    init {
        v.setOnClickListener {
            onMenuItemClickListener?.onMenuItemClick(data)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(data: MenuData) {
        this.data = data
        txtMain.text = data.name
        txtSub.text = data.descriptionShort
        txtTime.text = "${data.cookingTime} 분"
        txtLevel.text = data.level

        checkbox.isChecked = data.favorite
    }

    fun setOnMenuClickListener(listener: OnMenuItemClickListener) {
        this.onMenuItemClickListener = listener
    }
}