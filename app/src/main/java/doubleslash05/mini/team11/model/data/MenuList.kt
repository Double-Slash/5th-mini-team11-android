package doubleslash05.mini.team11.model.data

import com.google.gson.annotations.SerializedName

data class MenuList(
    @SerializedName("title") var mainTitle: String,
    @SerializedName("subtitle") var subTitle: String,
    @SerializedName("menu_list") val menuList: List<MenuData>,
    var horizon: Boolean
) {
    companion object {
        fun getSample(): MenuList {
            return MenuList(
                "TTT",
                "SSS",
                listOf(
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample()
                ),
                false
            )
        }
        fun getSampleHorizon(): MenuList {
            return MenuList(
                "H - TTT",
                "H - SSS",
                listOf(
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample(),
                    MenuData.getSample()
                ),
                true
            )
        }
    }
}