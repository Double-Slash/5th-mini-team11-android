package doubleslash05.mini.team11.model.data

import com.google.gson.annotations.SerializedName

data class NutritionData(
    @SerializedName("nutrition_info") val info: HashMap<String, String>,
    val calorie: Int
)