package com.weredev.usecase_test.domain.usecase

import com.weredev.usecase.BaseAsyncUseCase
import com.weredev.usecase_test.domain.repository.RepositoryBackEnd

class GetMessageFromBackEndUseCase(
    private val repositoryBackEnd: RepositoryBackEnd
): BaseAsyncUseCase<String, String>() {
    override suspend fun invoke(params: String?): String {
        return repositoryBackEnd.getMessageAfter5Sec(params ?: "")
    }
}