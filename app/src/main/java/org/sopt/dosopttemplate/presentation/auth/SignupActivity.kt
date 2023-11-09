package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.EMPTY_ERROR
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.ID_ERROR
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.PWD_ERROR
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSignUpState()
        clickListeners()
        hideKeyboard()
    }

    private fun observeSignUpState() {
        viewModel.signUpState.observe(this) { state ->
            when (state) {
                is UiState.Success -> intentToLogin()
                is UiState.Failure -> {
                    when (state.type) {
                        ID_ERROR -> binding.root.showSnackbar(getString(R.string.signup_failed_id))
                        PWD_ERROR -> binding.root.showSnackbar(getString(R.string.signup_failed_pwd))
                        EMPTY_ERROR -> binding.root.showSnackbar(getString(R.string.signup_failed_empty))
                    }
                }
            }
        }
    }

    private fun clickListeners() {
        binding.btnSignupBottom.setOnClickListener {
            user = User(
                binding.edtSignupId.text.toString(),
                binding.edtSignupPwd.text.toString(),
                binding.edtSignupNickname.text.toString(),
                binding.edtSignupMbti.text.toString(),
            )
            viewModel.signUp(user.toUser())
        }
    }

    private fun intentToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(USER_INFO, user)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun hideKeyboard() {
        binding.clSignupSecond.setOnClickListener {
            it.hideKeyboard()
        }
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
