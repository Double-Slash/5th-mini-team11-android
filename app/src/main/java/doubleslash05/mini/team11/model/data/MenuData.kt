package doubleslash05.mini.team11.model.data

data class MenuData(
    val id: Int,
    val name: String,
    val cookingTime: Int,
    val level: String,
    val descriptionShort: String,
    val thumbnailUrl: String,
    val favorite: Boolean
){

    companion object{
        fun getSample() : MenuData {
            return MenuData(
                0,
                "Test Title",
                15,
                "초급",
                "Sub Test Title",
                "http",
                false
            )
        }
    }
}