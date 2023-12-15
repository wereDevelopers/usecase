package com.weredev.usecase

abstract class BaseAsyncUseCase<Y, in Params>() {
    abstract suspend operator fun invoke(params: Params? = null):Y
}