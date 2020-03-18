package com.ahrybuk.opencvtest.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val subscriptions = CompositeDisposable()

    override fun onCleared() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        super.onCleared()
    }

}