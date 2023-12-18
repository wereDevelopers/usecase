package com.weredev.usecase_test.domain.repository

interface RepositoryBackEnd {
    suspend fun getMessageAfter5Sec(id: String): String
}