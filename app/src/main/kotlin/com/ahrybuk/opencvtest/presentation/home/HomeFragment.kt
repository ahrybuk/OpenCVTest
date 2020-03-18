package com.ahrybuk.opencvtest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ahrybuk.opencvtest.R
import com.ahrybuk.opencvtest.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }

    override fun onResume() {
        super.onResume()
        setUpAppBar()
    }

    private fun bindListeners() {
        btnList.setOnClickListener {
            //            TODO navigate to list
        }
        btnStart.setOnClickListener {
            //            TODO navigate to camera screen
        }
    }

    private fun setUpAppBar() {
        (activity as? AppCompatActivity)?.run {
            view?.let {
                val innerToolbar = it.findViewById<Toolbar>(R.id.toolbar)
                setSupportActionBar(innerToolbar)
                supportActionBar?.title = getString(R.string.home_screen_title)
            }
        }
    }
}