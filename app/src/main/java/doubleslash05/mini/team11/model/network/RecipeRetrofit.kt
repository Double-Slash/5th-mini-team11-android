package doubleslash05.mini.team11.model.network

import androidx.lifecycle.LiveData
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.model.network.base.ApiStatus
import retrofit2.Call
import retrofit2.http.*

typealias RetroLiveData<T> = LiveData<ApiStatus<T>>

interface RecipeRetrofit {
    @GET("/recipe/popular")
    fun getCategoryPopular(
        @Query("category") category : String
    ): RetroLiveData<MenuList>

    @GET("/recipe/new")
    fun getCategoryNew(
        @Query("category") category : String
    ): RetroLiveData<MenuList>

    @POST("/recipe/favorites/{recipe_id}")
    fun setFavorite(
        @Path("recipe_id") recipeId : String
    ): RetroLiveData<Any>

    @DELETE("/recipe/favorites/{recipe_id}")
    fun unsetFavorite(
        @Path("recipe_id") recipeId : String
    ): RetroLiveData<Any>

    @POST("/recipe/made/{recipe_id}")
    fun setMade(
        @Path("recipe_id") recipeId : String
    )

    @GET("/recipe/made")
    fun getMadeList(): RetroLiveData<MenuList>

    @GET("/recipe/favorites")
    fun getFavoritesList(): RetroLiveData<MenuList>
}