package com.weredev.usecase_test.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.weredev.usecase.utils.ResourceState
import com.weredev.usecase.utils.ResponseInterface
import com.weredev.usecase_test.R
import com.weredev.usecase_test.ui.common.BaseActivity

class HomeActivity : BaseActivity() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getMessageFromBackEnd("id")
        initObserver()
        findViewById<TextView>(R.id.messageCache).text = homeViewModel.getMessageCache("id").toString()
    }

    private fun initObserver() {
        homeViewModel.messageBELiveData.observe(this) {
            val responseInterface = this
            if (it != null) {
                when (it.resourceState) {
                    ResourceState.LOADING -> {
                        responseInterface.onLoading(true)
                    }

                    ResourceState.SUCCESS -> {
                        responseInterface.onLoading(false)
                        findViewById<TextView>(R.id.messageBE).text = it.data

                    }

                    ResourceState.ERROR -> {
                        responseInterface.onLoading(false)
                        responseInterface.onError(it.error!!)
                    }
                }
            }
        }
    }
}
