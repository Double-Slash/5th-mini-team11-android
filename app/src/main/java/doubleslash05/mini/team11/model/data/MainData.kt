package doubleslash05.mini.team11.model.data

import com.google.gson.annotations.SerializedName

data class MainData (
    @SerializedName("main_list") val mainList : List<MenuList>
)