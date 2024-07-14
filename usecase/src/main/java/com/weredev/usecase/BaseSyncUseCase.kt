package com.weredev.usecase

/**
 * Abstract base class for synchronous use cases.
 *
 * @param Y The type of result returned by the use case.* @param Params The type of parameters accepted by the use case.
 */
abstract class BaseSyncUseCase<Y, in Params>() {
    /**
     * Executes the use case synchronously.
     *
     * @param params Optional parameters for the use case.
     * @return Theresult of the use case execution.
     */
    abstract operator fun invoke(params: Params? = null): Y
}