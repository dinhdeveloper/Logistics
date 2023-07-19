package com.dinhtc.logistics.view.fragment

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.dinhtc.logistics.R
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash

    override fun viewCreatedFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 3000)
    }
}