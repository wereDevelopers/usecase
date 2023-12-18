package com.weredev.usecase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weredev.usecase.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class BaseViewModel : ViewModel() {

    fun <T, Y> BaseAsyncUseCase<T, Y>.executeAndDispose(
        mutableLiveData: MutableLiveData<Resource<T>>,
        params: Y? = null
    ) {
        viewModelScope.launch {
            mutableLiveData.postValue(Resource.loading())
            println("BaseViewModel loading")
            val scope = Dispatchers.IO
            try {
                withContext(scope) {
                    val response = this@executeAndDispose.invoke(params)
                    println("BaseViewModel success")
                    mutableLiveData.postValue(Resource.success(response))
                    scope.cancel()
                }
            } catch (e: Exception) {
                println("BaseViewModel error")

                mutableLiveData.postValue(Resource.error(e))
                scope.cancel()
            }
        }
    }


}