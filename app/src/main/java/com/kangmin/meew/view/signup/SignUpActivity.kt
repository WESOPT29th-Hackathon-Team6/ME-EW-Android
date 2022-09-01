package com.kangmin.meew.view.signup

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.kangmin.base.BaseActivity
import com.kangmin.meew.R
import com.kangmin.meew.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedkeyboardobserver.TedKeyboardObserver

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                kotlin.runCatching {
                    Navigation.findNavController(this@SignUpActivity, R.id.nav_host_fragment)
                }.onSuccess {
                    when(it.currentDestination?.id) {
                        R.id.characterSelectFragment -> {
                            finish()
                        }
                        R.id.nickNameFragment -> {
                            it.navigateUp()
                        }
                    }
                }
            }
        }
        )
    }

    override fun setObserve() {
        super.setObserve()

        TedKeyboardObserver(this).listen { isShow ->
            if (!isShow) {
                binding.root.clearFocus()
            }
        }
    }

    override fun getIntentData() {
        super.getIntentData()
        viewModel.kakaoToken = intent.getStringExtra("kakao_token") ?: ""
    }
}