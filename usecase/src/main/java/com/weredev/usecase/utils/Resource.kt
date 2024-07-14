package com.weredev.usecase.utils

/**
 * A generic class that holds a value with its loading status and potential error.
 *
 * @param T The type of the resource data.
 */
data class Resource<out T>(val resourceState: ResourceState, val data: T?, val error: Throwable?) {

    companion object {
        /*** Creates a [Resource] with a successful status and the provided data.
         *
         * @param data The data to wrap in the [Resource].
         * @return A [Resource] with a successful status.
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        /**
         * Creates a [Resource] with an error status, the provided error, and optional data.
         *
         * @param error The error to wrap in the [Resource].
         * @param data Optional data to include in the [Resource].
         * @return A [Resource] with an error status.
         */
        fun <T> error(error: Throwable, data: T? = null): Resource<T> {
            return Resource(ResourceState.ERROR, data, error)
        }

        /**
         * Creates a [Resource] with a loading status.
         *
         * @return A [Resource] with a loading status.
         */
        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.LOADING, null, null)
        }
    }
}