package doubleslash05.mini.team11.model.network

import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.util.extension.RetroLiveData
import retrofit2.http.*


interface RecipeRetrofit {

    @GET("/recipe/{recipe_id}")
    fun getRecipeDetail(
        @Path("recipe_id") recipeId: Int
    ) : RetroLiveData<RecipeData>

    @GET("/recipe/favorites")
    fun getFavoritesList(): RetroLiveData<MenuList>

    @POST("/recipe/favorites/{recipe_id}")
    fun setFavorite(
        @Path("recipe_id") recipeId: Int
    ): RetroLiveData<Any>

    @DELETE("/recipe/favorites/{recipe_id}")
    fun unsetFavorite(
        @Path("recipe_id") recipeId: Int
    ): RetroLiveData<Any>

    @GET("/recipe/made")
    fun getMadeList(): RetroLiveData<MenuList>

    @POST("/recipe/made/{recipe_id}")
    fun setMade(
        @Path("recipe_id") recipeId: Int
    ): RetroLiveData<Any>

    @GET("/recipe/new")
    fun getCategoryNew(
        @Query("category") category: String
    ): RetroLiveData<MenuList>

    @GET("/recipe/popular")
    fun getCategoryPopular(
        @Query("category") category: String
    ): RetroLiveData<MenuList>

    @GET("/recipe/search")
    fun searchRecipe(
        @Query("keyword") keyword: String
    )
}