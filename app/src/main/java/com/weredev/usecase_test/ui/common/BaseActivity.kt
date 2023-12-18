package com.weredev.usecase_test.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.weredev.usecase.utils.ResponseInterface

open class BaseActivity: AppCompatActivity(), ResponseInterface {
    override fun onLoading(isLoading: Boolean) {
        //TODO utile a gestire onLoading generale
    }

    override fun onError(error: Throwable) {
        //TODO utile a gestire onError generale
    }
}