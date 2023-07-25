package com.dinhtc.logistics.view.activity

import androidx.navigation.findNavController
import com.dinhtc.logistics.R
import com.dinhtc.logistics.common.view.BaseActivity
import com.dinhtc.logistics.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreateActivity() {
        findNavController(R.id.navhost)
    }
}