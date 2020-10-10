package doubleslash05.mini.team11.ui.category

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.MenuData
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.util.extension.dpToPx
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.item_title.view.*

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: List<MenuList>? = null
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
            VIEW_TYPE_HORIZON -> {
                val v = RecyclerView(parent.context)
                return HorizonRecyclerViewHolder(v)
            }
            else -> {
                return HiddenViewHolder(parent.context)
            }
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        if (list == null) return 0
        for (element in list!!) {
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
            is HorizonRecyclerViewHolder -> {
                val data = getInfoData(position) ?: return
                holder.recyclerView.adapter = HorizontalMenuAdapter(data.menuList)
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
        if (list == null) return null
        for (element in list!!) {
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

    private inner class HorizonRecyclerViewHolder(val recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView) {
        init {
            recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private inner class MenuViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val background = v.imageview_menu_background!!
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

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                val data = getMenuData(position) ?: return@setOnCheckedChangeListener
                RecipeModel.setFavorite(data.id, isChecked)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: MenuData) {
            Glide.with(background.context).load(data.thumbnailUrl).into(background)

            txtMain.text = data.name
            txtSub.text = data.descriptionShort
            txtTime.text = "${data.cookingTime} 분"
            txtLevel.text = data.level

            checkbox.isChecked = data.favorite
        }
    }

    private data class InternalData(val viewType: Int, val info: MenuList, val data: MenuData?)

    private inner class HorizontalMenuAdapter(val list: List<MenuData>) : RecyclerView.Adapter<MenuViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val context = parent.context
            val v = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
            v.layoutParams = FrameLayout.LayoutParams(context.dpToPx(286f), context.dpToPx(132f)).apply {
                leftMargin = context.dpToPx(10f)
                rightMargin = context.dpToPx(10f)
                topMargin = context.dpToPx(20f)
                bottomMargin = context.dpToPx(10f)
            }

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
