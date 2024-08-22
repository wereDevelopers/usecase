package com.weredev.usecase_test.domain.usecase

import com.weredev.usecase.BaseSyncUseCase

class GetStartMessageUseCase: BaseSyncUseCase<String?, Unit?>() {
    override fun invoke(params: Unit?): String {
        return "Wait for the response..."
    }
}