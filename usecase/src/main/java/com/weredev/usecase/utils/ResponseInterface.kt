package com.weredev.usecase.utils

import androidx.lifecycle.LifecycleOwner

/**
 * An interface for observing the loading and error states of an asynchronous operation.
 */
interface ResponseInterface : LifecycleOwner {
    /**
     * Called when the loading state changes.
     *
     * @param isLoading `true` if the operation is currently loading, `false` otherwise.
     */
    fun onLoading(isLoading: Boolean)

    /**
     * Called when an error occurs during the operation.*
     * @param error The error that occurred.
     */
    fun onError(error: Throwable)
}