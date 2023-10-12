package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.util.extension.showToast

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSignUpResult()
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnSignupBottom.setOnClickListener {
            userInfo = UserInfo(
                binding.edtSignupId.text.toString(),
                binding.edtSignupPwd.text.toString(),
                binding.edtSignupNickname.text.toString(),
                binding.edtSignupMbti.text.toString(),
            )
            viewModel.checkSignUpValid(userInfo)
        }
    }

    private fun observeSignUpResult() {
        viewModel.isSignUpValid.observe(this) {
            if (it == true) {
                intentUserInfo()
            } else {
                binding.root.showToast(getString(R.string.signup_failed))
            }
        }
    }

    private fun intentUserInfo() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(USER_INFO, userInfo)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
