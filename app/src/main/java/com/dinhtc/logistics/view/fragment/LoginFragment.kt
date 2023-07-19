package com.dinhtc.logistics.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dinhtc.logistics.R
import com.dinhtc.logistics.common.api.ApiHelperImpl
import com.dinhtc.logistics.common.api.RetrofitBuilder
import com.dinhtc.logistics.common.local.DatabaseBuilder
import com.dinhtc.logistics.common.local.DatabaseHelperImpl
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentLoginBinding
import com.dinhtc.logistics.utils.DefaultDispatcherProvider
import com.dinhtc.logistics.utils.SharedPreferencesManager
import com.dinhtc.logistics.utils.SharedPreferencesManager.*
import com.dinhtc.logistics.utils.SharedPreferencesManager.Companion.IS_LOGGED_IN
import com.dinhtc.logistics.utils.SharedPreferencesManager.Companion.LAST_LOGIN_TINE
import com.dinhtc.logistics.utils.SharedPreferencesManager.Companion.PASS_W
import com.dinhtc.logistics.utils.SharedPreferencesManager.Companion.USERNAME
import com.dinhtc.logistics.utils.UiState
import com.dinhtc.logistics.utils.ViewModelFactory
import com.dinhtc.logistics.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var userViewModel: UserViewModel
    private var checkShowEyePass: Boolean = false

    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    override fun viewCreatedFragment() {
        context?.let { SharedPreferencesManager.init(it) }
        checkAutoLogin()
        setupViewModel()
        actionView()
    }

    private fun checkAutoLogin() {
        val isLoggedIn = SharedPreferencesManager.instance.getBoolean(IS_LOGGED_IN, false)
        val lastLoginTime = SharedPreferencesManager.instance.getLong(LAST_LOGIN_TINE, 0)
        if (isLoggedIn) {
            // Kiểm tra thời gian đăng nhập gần nhất
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - lastLoginTime
            val maxLoginDuration = (7 * 24 * 60 * 60 * 1000).toLong() // 7 ngày
            if (elapsedTime <= maxLoginDuration) {
                // Đăng nhập tự động
                // Chuyển đến màn hình chính
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                // Đăng xuất người dùng
                logout()
            }
        }
    }

    // Đăng xuất người dùng
    private fun logout() {
        // Xóa thông tin đăng nhập từ SharedPreferences
        SharedPreferencesManager.instance.remove(USERNAME)
        SharedPreferencesManager.instance.remove(IS_LOGGED_IN)
        SharedPreferencesManager.instance.remove(LAST_LOGIN_TINE)
        // Chuyển đến màn hình đăng nhập
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(activity?.applicationContext!!)),
                DefaultDispatcherProvider()
            )
        )[UserViewModel::class.java]
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun actionView() {
        viewBinding.btnStart.setOnClickListener {
            if (checkValidate()){
                rememberLogin(
                    viewBinding.edtUsername.text.toString().trim(),
                    viewBinding.edtPassword.text.toString().trim()
                )
            }
        }

        viewBinding.edtUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    viewBinding.edtUsername.background = context?.let { it1 ->
                        ContextCompat.getDrawable(
                            it1, R.drawable.bg_button_strock
                        )
                    }
                }
            }

        })

        viewBinding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    viewBinding.edtPassword.background = context?.let { it1 ->
                        ContextCompat.getDrawable(
                            it1, R.drawable.bg_button_strock
                        )
                    }
                }
            }

        })

        viewBinding.edtPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= viewBinding.edtPassword.right - viewBinding.edtPassword.compoundDrawables.get(
                        DRAWABLE_RIGHT
                    )?.bounds?.width()!!
                ) {
                    if (checkShowEyePass) {
                        viewBinding.edtPassword.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                        viewBinding.edtPassword.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            context?.let {
                                ContextCompat.getDrawable(it, R.drawable.eye_off)
                            }, null
                        )
                    } else {
                        viewBinding.edtPassword.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        viewBinding.edtPassword.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            context?.let {
                                ContextCompat.getDrawable(it, R.drawable.eye_open)
                            }, null
                        )
                    }
                    checkShowEyePass = !checkShowEyePass
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun checkValidate(): Boolean {
        if (viewBinding.edtUsername.text.toString().trim() == "admin" && viewBinding.edtPassword.text.toString().trim() == "123456"){
            viewBinding.edtUsername.background = context?.let { it1 ->
                ContextCompat.getDrawable(
                    it1, R.drawable.bg_button_strock
                )
            }
            viewBinding.edtPassword.background = context?.let { it1 ->
                ContextCompat.getDrawable(
                    it1, R.drawable.bg_button_strock
                )
            }
            return true
        }
        else {
            viewBinding.edtUsername.background = context?.let { it1 ->
                ContextCompat.getDrawable(
                    it1, R.drawable.bg_button_strock_red
                )
            }
            viewBinding.edtPassword.background = context?.let { it1 ->
                ContextCompat.getDrawable(
                    it1, R.drawable.bg_button_strock_red
                )
            }
            return false
        }
    }

    private fun rememberLogin(edtUsername : String,edtPassword : String) {
        SharedPreferencesManager.instance.putString(USERNAME, edtUsername)
        SharedPreferencesManager.instance.putString(PASS_W, edtUsername)
        SharedPreferencesManager.instance.putBoolean(IS_LOGGED_IN, true)
        SharedPreferencesManager.instance.putLong(LAST_LOGIN_TINE, System.currentTimeMillis())
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            Log.e("SSSSSSSSSSS", "${Gson().toJson(it.data)}")
                        }

                        is UiState.Loading -> {}
                        is UiState.Error -> {}
                    }
                }
            }
        }
    }
}