package com.weredev.usecase_test.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.weredev.usecase.observeWithResource
import com.weredev.usecase_test.R
import com.weredev.usecase_test.ui.common.BaseActivity

class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViewById<TextView>(R.id.messageBE).text = homeViewModel.getStartMessage(this)
        homeViewModel.getMessageFromBackEnd("id")
        initObserver()
        findViewById<TextView>(R.id.messageCache).text = homeViewModel.getMessageCache("id")
    }

    private fun initObserver() {
        homeViewModel.messageBELiveData.observeWithResource(this,
            onLoading = {
                //Is loading
            },
            onError = {
                findViewById<TextView>(R.id.messageBE).setText(R.string.error)
            },
            onSuccess = {
                findViewById<TextView>(R.id.messageBE).text = it
            })
    }
}
