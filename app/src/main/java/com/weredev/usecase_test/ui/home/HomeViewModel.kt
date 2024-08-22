package com.weredev.usecase_test.ui.home

import androidx.lifecycle.MutableLiveData
import com.weredev.usecase.BaseViewModel
import com.weredev.usecase.executeWithCatch
import com.weredev.usecase.utils.Resource
import com.weredev.usecase.utils.ResponseInterface
import com.weredev.usecase_test.data.repository.RepositoryBackEndImpl
import com.weredev.usecase_test.data.repository.RepositoryCacheImpl
import com.weredev.usecase_test.domain.usecase.GetMessageFromBackEndUseCase
import com.weredev.usecase_test.domain.usecase.GetMessageFromCacheUseCase
import com.weredev.usecase_test.domain.usecase.GetStartMessageUseCase

class HomeViewModel : BaseViewModel() {
    private val repoCache = RepositoryCacheImpl()
    private val repoBE = RepositoryBackEndImpl()
    private val getMessageFromBackEndUseCase = GetMessageFromBackEndUseCase(repoBE)
    private val getMessageFromCacheUseCase = GetMessageFromCacheUseCase(repoCache)
    private val getStartMessageUseCase = GetStartMessageUseCase()

    val messageBELiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getMessageFromBackEnd(id: String) {
        getMessageFromBackEndUseCase.executeAndDispose(messageBELiveData, id)
    }

    fun getMessageCache(id: String): String {
        return getMessageFromCacheUseCase.invoke(id)
    }
    fun getStartMessage(responseInterface: ResponseInterface): String? {
        return getStartMessageUseCase.executeWithCatch(responseInterface)
    }
}