package doubleslash05.mini.team11.model.data

data class MenuList(
    val mainTitle: String,
    val subTitle: String,
    val menuList: List<MenuData>,
    val horizon: Boolean
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
    }
}