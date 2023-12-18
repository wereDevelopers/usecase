package com.weredev.usecase_test.data.repository

import com.weredev.usecase_test.domain.repository.RepositoryBackEnd
import kotlinx.coroutines.delay

class RepositoryBackEndImpl: RepositoryBackEnd {
    override suspend fun getMessageAfter5Sec(id: String): String {
        delay(5000)
        return when (id){
            "id" -> "Message OK"
            else -> "Message KO"
        }
    }
}