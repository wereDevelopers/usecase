# Usecase

[![Jitpack](https://jitpack.io/v/wereDevelopers/usecase.svg)](https://jitpack.io/#wereDevelopers/usecase)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wereDevelopers/usecase/blob/main/LICENSE)

We have created this library to help us stick to clean architecure principle creating a domain layer were to place all the business logic and final entities and at the same time to handle threads, coroutine, livedata and all the update from the process like loading, error and result.

You can create your own usecase extending BaseAsyncUsecase or BaseSyncUsecase and when you need to execute the method you call executeAndDispose for async and executeWithCatch for sync.

To listen to the result you can call observeWithResource that help to differentiet from loading, error or result values.

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
