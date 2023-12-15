package com.weredev.usecase

import androidx.lifecycle.LifecycleOwner

interface ResponseInterface: LifecycleOwner {

    fun onLoading(isLoading: Boolean)

    fun onError(error: Throwable)
}