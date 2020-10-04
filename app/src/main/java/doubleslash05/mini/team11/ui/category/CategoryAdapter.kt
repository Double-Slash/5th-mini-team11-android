package doubleslash05.mini.team11.ui.category

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.model.data.MenuList
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.item_title.view.*

class CategoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var list: List<MenuList>
    private var onMenuItemClickListener: OnMenuItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_TITLE -> {
                val v = inflater.inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(v)
            }
            VIEW_TYPE_MENU -> {
                val v = inflater.inflate(R.layout.item_menu, parent, false)
                return MenuViewHolder(v)
            }
            else -> {
                return HiddenViewHolder(parent.context)
            }
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        for (element in list) {
            if (element.horizon) { // 가로 모드일 경우에는 2개만 추가
                count += 2
                continue
            }

            val size = element.menuList.size
            count += if (size == 0) 0 else size + 1
        }
        return count
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                val data = getInfoData(position) ?: return
                holder.mainTitle.text = data.mainTitle
                holder.subTitle.text = data.subTitle
            }
            is MenuViewHolder -> {
                val data = getMenuData(position) ?: return
                holder.bind(data)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = getInternalData(position) ?: return -1

        return data.viewType
    }

    fun setData(list: List<MenuList>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getInternalData(position: Int): InternalData? {
        var count = 0
        for (element in list) {
            val size = element.menuList.size
            if (size == 0) continue

            if (count == position) return InternalData(VIEW_TYPE_TITLE, element, null)
            if (element.horizon) {
                if (count + 1 == position) return InternalData(VIEW_TYPE_HORIZON, element, null)
                count += 2
                continue
            }

            val preCount = count
            count += size + 1
            if (position < count) return InternalData(VIEW_TYPE_MENU, element, element.menuList[position - preCount - 1])
        }

        return null
    }

    fun getInfoData(position: Int): MenuList? {
        val d = getInternalData(position)
        return d?.info
    }

    // Adapter position으로 MenuData 가져오기
    fun getMenuData(position: Int): MenuData? {
        val d = getInternalData(position)
        return d?.data
    }

    fun setOnMenuItemClickListener(listener: OnMenuItemClickListener) {
        this.onMenuItemClickListener = listener
    }

    private inner class HiddenViewHolder(context: Context) : RecyclerView.ViewHolder(View(context))
    private inner class TitleViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val mainTitle = v.textview_title_main!!
        val subTitle = v.textview_title_sub!!
    }

    private inner class MenuViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtMain = v.textview_menu_main!!
        val txtSub = v.textview_menu_sub!!
        val txtTime = v.textview_menu_time!!
        val txtLevel = v.textview_menu_level!!
        val checkbox = v.checkbox_menu_favorite!!

        init {
            v.setOnClickListener {
                val position = adapterPosition
                val data = getMenuData(position) ?: return@setOnClickListener
                onMenuItemClickListener?.onMenuItemClick(data)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: MenuData) {
            txtMain.text = data.name
            txtSub.text = data.descriptionShort
            txtTime.text = "${data.cookingTime} 분"
            txtLevel.text = data.level

            checkbox.isChecked = data.favorite
        }
    }

    private data class InternalData(val viewType: Int, val info: MenuList, val data: MenuData?)

    private inner class HorizontalMenuAdapter(val list: List<MenuData>) : RecyclerView.Adapter<MenuViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
            return MenuViewHolder(v)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            holder.bind(list[position])
        }
    }

    interface OnMenuItemClickListener {
        fun onMenuItemClick(data: MenuData)
    }

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_MENU = 1
        private const val VIEW_TYPE_HORIZON = 2
    }
}
