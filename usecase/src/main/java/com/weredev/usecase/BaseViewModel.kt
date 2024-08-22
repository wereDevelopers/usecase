package com.weredev.usecase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weredev.usecase.utils.Resource


abstract class BaseViewModel : ViewModel() {

    /**
     * Extension function to execute an asynchronous use case within the ViewModel's scope and post the result to a MutableLiveData.
     *
     * @param T The type of the result returned by the use case.
     * @param Y The type of the parameters passed to the use case.
     * @param mutableLiveData The MutableLiveData to post the results to.
     * @param params The parameters to pass to the use case. Can be null.
     */
    fun <T, Y> BaseAsyncUseCase<T, Y>.executeAndDispose(
        mutableLiveData: MutableLiveData<Resource<T>>,
        params: Y? = null
    ) {
        this.executeAndDispose(viewModelScope, mutableLiveData, params)
    }


}