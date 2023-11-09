package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.auth.LoginViewModel.Companion.LOGIN_ERROR
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var signUpResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResultLauncher()
        observeLoginState()
        hideKeyboard()
        clickListeners()
    }

    private fun initResultLauncher() {
        signUpResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == RESULT_OK) {
                    val data = result.data ?: return@registerForActivityResult
                    data.getParcelableExtra<User>(USER_INFO).apply {
                        this?.let {
                            viewModel.saveUser(it.toUser())
                            Log.d("login", "it.toUser():::::::: ${it.toUser()}")
                            Log.d("login", "signUpInfo:::::::: ${viewModel.user}")
                            binding.root.showSnackbar(getString(R.string.signup_success))
                        }
                    }
                } else {
                    return@registerForActivityResult
                }
            }
    }

    private fun observeLoginState() {
        viewModel.loginState.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    binding.root.showSnackbar(getString(R.string.login_success))
                    intentToProfileActivity()
                }

                is UiState.Failure -> {
                    if (state.type == LOGIN_ERROR) {
                        binding.root.showSnackbar(getString(R.string.login_failed))
                    }
                }
            }
        }
    }

    private fun clickListeners() {
        with(binding) {
            btnLoginBottom.setOnClickListener {
                login()
            }
            tvLoginSignup.setOnClickListener {
                signUpResultLauncher.launch(Intent(this@LoginActivity, SignupActivity::class.java))
            }
        }
    }

    private fun login() {
        val inputId = binding.edtLoginId.text.toString()
        val inputPwd = binding.edtLoginPwd.text.toString()

        if (inputId.isNotBlank() && inputPwd.isNotBlank()) {
            val loginInfo = User(
                id = inputId,
                pwd = inputPwd,
                "",
                "",
                "",
            )
            viewModel.login(loginInfo.toUser(), viewModel.user)
        } else {
            binding.root.showSnackbar(getString(R.string.signup_failed_empty))
        }
    }

    private fun intentToProfileActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun hideKeyboard() {
        binding.clLoginMain.setOnClickListener {
            it.hideKeyboard()
        }
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
