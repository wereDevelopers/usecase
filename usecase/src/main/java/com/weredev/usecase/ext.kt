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
 * Extension function for observing a MutableLiveData<Resource<T>> with customizable callbacks for different states.
 *
 * @param T The type of data contained in the Resource.
 * @param responseInterface The interface for handling loading and error states.
 * @param onError Optional callback to handle errors. If not provided, the responseInterface.onError will be used.
 * @param onLoading Optional callback to handle loading state. If not provided, the responseInterface.onLoading will be used.
 * @param onSuccess Callback to handle the success state.
 */
fun <T> MutableLiveData<Resource<T>>.observeWithResource(
    responseInterface: ResponseInterface,
    onError: ((Throwable) -> Unit)? = null,
    onLoading: ((isLoading: Boolean) -> Unit)? = null,
    onSuccess: (T?) -> Unit
) {
    this.removeObservers(responseInterface)
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
 * Extension function to execute an asynchronous use case and post its result to a MutableLiveData.
 *
 * @param T The type of the result returned by the use case.
 * @param Y The type of the parameters passed to the use case.
 * @param coroutineScope The CoroutineScope in which to execute the use case.
 * @param mutableLiveData The MutableLiveData to post the results to.
 * @param params The parameters to pass to the use case. Can be null.
 */
fun <T, Y> BaseAsyncUseCase<T, Y>.executeAndDispose(
    coroutineScope: CoroutineScope,
    mutableLiveData: MutableLiveData<Resource<T>>,
    params: Y? = null
) {
    coroutineScope.launch {
        mutableLiveData.postValue(Resource.loading())
        println("executeAndDispose loading")
        val scope = Dispatchers.IO
        try {
            withContext(scope) {
                val response = this@executeAndDispose.invoke(params)
                println("executeAndDispose success")
                mutableLiveData.postValue(Resource.success(response))
                scope.cancel()
            }
        } catch (e: Exception) {
            println("executeAndDispose error")
            mutableLiveData.postValue(Resource.error(e))
            scope.cancel()
        }
    }
}

/**
 * Extension function to execute a synchronous use case with error handling.
 *
 * @param T The type of the result returned by the use case.
 * @param Y The type of the parameters passed to the use case.
 * @param responseInterface The interface for handling errors.
 * @param params The parameters to pass to the use case. Can be null.
 * @param onError Optional callback to handle errors. If not provided, the responseInterface.onError will be used.
 * @return The result of the use case, or null if an error occurred.
 */
fun <T, Y> BaseSyncUseCase<T, Y>.executeWithCatch(
    responseInterface: ResponseInterface,
    params: Y? = null,
    onError: ((Throwable) -> Unit)? = null,
): T? {
    return try {
        this@executeWithCatch.invoke(params)
    } catch (e: Exception) {
        if(onError != null) {
            onError(e)
        }
        else
        {
            responseInterface.onError(e)
        }
        null
    }
}