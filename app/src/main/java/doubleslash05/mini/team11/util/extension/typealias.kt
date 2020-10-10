package doubleslash05.mini.team11.util.extension

import androidx.lifecycle.LiveData
import doubleslash05.mini.team11.model.network.base.ApiStatus

typealias RetroLiveData<T> = LiveData<ApiStatus<T>>