package com.weredev.usecase_test.domain.repository

interface RepositoryCache {
    fun getMessage(id: String): String
}