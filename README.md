# Usecase

[![Jitpack](https://jitpack.io/v/wereDevelopers/usecase.svg)](https://jitpack.io/#wereDevelopers/usecase)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wereDevelopers/usecase/blob/main/LICENSE)

Usecase is used for managing threads in apps while maintaining clean architecture

## How to implement:

add in the Gradle

```groovy
dependencies {
    implementation('com.github.wereDevelopers:usecase:{LastTag}')
}
```


## How to use



### Activity:
```
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
	...
```


### ViewModel:
```
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.FragmentMainBinding

class HomeViewModel : BaseViewModel() {
    private val repoCache = RepositoryCacheImpl()
    private val repoBE = RepositoryBackEndImpl()
    private val getMessageFromBackEndUseCase = GetMessageFromBackEndUseCase(repoBE)
    private val getMessageFromCacheUseCase = GetMessageFromCacheUseCase(repoCache)

    val messageBELiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getMessageFromBackEnd(id: String) {
        getMessageFromBackEndUseCase.executeAndDispose(messageBELiveData, id)
    }

    fun getMessageCache(id: String) {
        getMessageFromCacheUseCase.invoke(id)
    }
}
```
