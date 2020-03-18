package com.ahrybuk.opencvtest.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ahrybuk.opencvtest.R
import com.ahrybuk.opencvtest.presentation.base.BaseFragment

class SplashFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        val splashViewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]

        splashViewModel.countDownTimerLiveData.observe(viewLifecycleOwner, Observer {
            //            TODO() navigate
        })
    }
}