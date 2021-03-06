package doubleslash05.mini.team11.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import doubleslash05.mini.team11.model.data.MainData
import doubleslash05.mini.team11.model.data.MenuList
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.model.network.base.RestClient
import doubleslash05.mini.team11.util.extension.RetroLiveData


object RecipeModel {

    private val service by lazy { RestClient.getRecipeService() }

    fun getCategory(category: String): RetroLiveData<List<MenuList>> {
        val result = MediatorLiveData<ApiStatus<List<MenuList>>>()
        val list = ArrayList<MenuList>()
        val newLiveData = service.getCategoryNew(category)
        val popularLiveDouble = service.getCategoryPopular(category)


        fun merge(liveData: RetroLiveData<MenuList>) {
            when (val status = liveData.value) {
                is ApiStatus.Loading -> return
                is ApiStatus.Success -> {
                    if (list.size == 2) {
                        result.value = ApiStatus.Success(status.code, list)
                    }
                }
                is ApiStatus.Error -> {
                    result.value = status
                }
            }

            result.removeSource(liveData)
        }

        result.value = ApiStatus.Loading
        result.addSource(popularLiveDouble) {
            if (it is ApiStatus.Success) {
                it.data.horizon = true
                it.data.mainTitle = "인기 메뉴"
                it.data.subTitle = "이번주 가장 핫한 레시피"
                list.add(0, it.data)
            }
            merge(popularLiveDouble)
        }
        result.addSource(newLiveData) {
            if (it is ApiStatus.Success) {
                it.data.mainTitle = "최신 메뉴"
                it.data.subTitle = "새 레시피에 도전해보세요!"
                list.add(it.data)
            }
            merge(newLiveData)
        }

        return result
    }

    fun getFavoritesList(): RetroLiveData<MenuList> {
        return service.getFavoritesList()
    }

    fun getMadeMenuList(): RetroLiveData<MenuList> {
        return service.getMadeList()
    }

    fun setFavorite(recipeId: Int, isChecked: Boolean) {
        if (isChecked)
            service.setFavorite(recipeId)
        else
            service.unsetFavorite(recipeId)
    }

    fun getRecipeDetail(recipeId: Int): RetroLiveData<RecipeData>{
        return service.getRecipeDetail(recipeId)
    }

    fun setMade(recipeId: Int): RetroLiveData<Any> {
        return service.setMade(recipeId)
    }

    fun getMain() : RetroLiveData<MainData>{
        return service.getMain()
    }
}