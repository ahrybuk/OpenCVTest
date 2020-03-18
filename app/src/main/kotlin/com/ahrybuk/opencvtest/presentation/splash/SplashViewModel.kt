package com.ahrybuk.opencvtest.presentation.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ahrybuk.opencvtest.presentation.base.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel : BaseViewModel() {

    val countDownTimerLiveData = MutableLiveData<Unit>()

    init {
        startCountDown()
    }

    private fun startCountDown() {
        subscriptions.add(
            Observable.just(Unit)
                .delay(5, TimeUnit.SECONDS)
                .subscribe({
                    countDownTimerLiveData.postValue(it)
                }, {
                    Log.d("DebugTag", it.localizedMessage)
                })
        )
    }
}