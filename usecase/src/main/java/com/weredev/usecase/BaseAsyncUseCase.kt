package com.weredev.usecase

/**
 * Abstract base class for asynchronous use cases.
 *
 * @param Y The type of result returned by the use case.* @param Params The type of parameters accepted by the use case.
 */
abstract class BaseAsyncUseCase<Y, in Params>() {
    /**
     * Executes the use case asynchronously.
     *
     * @param params Optional parameters for the use case.
     * @return The resultof the use case execution.
     */
    abstract suspend operator fun invoke(params: Params? = null): Y
}