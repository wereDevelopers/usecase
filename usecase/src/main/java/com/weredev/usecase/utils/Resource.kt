package com.weredev.usecase.utils

data class Resource<out T>(val resourceState: ResourceState, val data: T?, val error: Throwable?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable, data: T? = null): Resource<T> {
            return Resource(ResourceState.ERROR, data, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.LOADING, null, null)
        }

    }

}