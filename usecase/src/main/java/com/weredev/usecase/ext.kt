package com.weredev.usecase

import androidx.lifecycle.MutableLiveData
import com.weredev.usecase.utils.Resource
import com.weredev.usecase.utils.ResourceState
import com.weredev.usecase.utils.ResponseInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Observes a [MutableLiveData] containing a [Resource] object.
 *
 * @param T The type of data contained in the [Resource] object.
 * @param responseInterface An interface for handling loading and error states.
 * @param onError An optional lambda function to handle errors.
 * @param onLoading An optional lambda function to handle loading states.
 * @param onSuccess A lambda function to handle successful data emissions.
 */
fun <T> MutableLiveData<Resource<T>>.observeWithResource(
    responseInterface: ResponseInterface,
    onError: ((Throwable) -> Unit)? = null,
    onLoading: ((isLoading: Boolean) -> Unit)? = null,
    onSuccess: (T?) -> Unit
) {
    observe(responseInterface) {
        if (it != null) {
            when (it.resourceState) {
                ResourceState.LOADING -> {
                    if (onLoading != null) {
                        onLoading(true)
                    } else {
                        responseInterface.onLoading(true)
                    }
                }
                ResourceState.SUCCESS -> {
                    if (onLoading != null) {
                        onLoading(false)
                    } else {
                        responseInterface.onLoading(false)
                    }
                    onSuccess(it.data)
                }
                ResourceState.ERROR -> {
                    if (onLoading != null) {
                        onLoading(false)
                    } else {
                        responseInterface.onLoading(false)
                    }
                    if (onError != null) {
                        onError(it.error!!)
                    } else {
                        responseInterface.onError(it.error!!)
                    }
                }
            }
        }
    }
}

/**
 *Executes a [BaseAsyncUseCase] and disposes of the coroutine scope when finished.
 *
 * @param T The type of data returned by the [BaseAsyncUseCase].
 * @param Y The type of parameter passed to the [BaseAsyncUseCase].
 * @param coroutineScope The coroutine scope to use for execution.
 * @param mutableLiveData A [MutableLiveData] to observe the result of the [BaseAsyncUseCase].
 * @param params An optional parameter to pass to the [BaseAsyncUseCase].
 */
fun <T, Y> BaseAsyncUseCase<T, Y>.executeAndDispose(
    coroutineScope: CoroutineScope,
    mutableLiveData: MutableLiveData<Resource<T>>,
    params: Y? = null
) {
    coroutineScope.launch {
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