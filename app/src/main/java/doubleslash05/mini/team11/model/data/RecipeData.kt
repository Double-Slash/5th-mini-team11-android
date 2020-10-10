package doubleslash05.mini.team11.model.data

import com.google.gson.annotations.SerializedName

data class RecipeData(
    val id: Int,
    val name: String,
    @SerializedName("short_description") val descriptionShort: String,
    @SerializedName("long_description") val descriptionLong: String,
    val level: String,
    @SerializedName("cooking_time") val cookingTime: Int,
    @SerializedName("favorites") val favorite: Boolean,
    @SerializedName("made") val isMade: Boolean,
    @SerializedName("video_url") val videoUrl: String,
    val servings: Int,
    val step: List<List<String>>,
    val ms: List<Int>,
    val tags: List<String>,
    val nutrition: NutritionData,
    val ingredient: IngredientData
)