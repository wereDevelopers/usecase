package com.weredev.usecase_test.domain.usecase

import com.weredev.usecase.BaseSyncUseCase
import com.weredev.usecase_test.domain.repository.RepositoryCache

class GetMessageFromCacheUseCase(
    private val repositoryCache: RepositoryCache
): BaseSyncUseCase<String, String>() {
    override fun invoke(params: String?): String {
        return repositoryCache.getMessage(params ?: "")
    }
}