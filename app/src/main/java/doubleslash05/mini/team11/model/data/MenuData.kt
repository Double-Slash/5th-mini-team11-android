package doubleslash05.mini.team11.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MenuData(
    val id: Int,
    val name: String,
    @SerializedName("cooking_time") val cookingTime: Int,
    val level: String,
    @SerializedName("short_description") val descriptionShort: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("favorites") val favorite: Boolean
) {

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