package com.weredev.usecase

abstract class BaseSyncUseCase<Y, in Params>() {
    abstract operator fun invoke(params: Params? = null):Y
}