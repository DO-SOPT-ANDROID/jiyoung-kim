package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.presentation.home.ProfileActivity
import org.sopt.dosopttemplate.util.extension.showSnackbar

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var signUpInfo: UserInfo
    private lateinit var loginInfo: UserInfo
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                val data = result.data ?: return@registerForActivityResult
                data.getParcelableExtra<UserInfo>(USER_INFO).apply {
                    this?.let {
                        signUpInfo = it
                        binding.root.showSnackbar("회원가입 완료!")
                    }
                }
            } else {
                return@registerForActivityResult
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        clickListeners()
        getUserInfo()
        checkLoginValid()
    }

    private fun clickListeners() {
        with(binding) {
            btnLoginBottom.setOnClickListener {
                checkLoginValid()
            }
            tvLoginSignup.setOnClickListener {
                launcher.launch(Intent(this@LoginActivity, SignupActivity::class.java))
            }
        }
    }

    private fun getUserInfo() {
        val intent = intent
        signUpInfo =
            intent.getParcelableExtra(USER_INFO) ?: UserInfo("null", "null", "null", "null")

        Log.d(
            "Login",
            "signUpInfo:: ${signUpInfo.id} ${signUpInfo.pwd} ${signUpInfo.name} ${signUpInfo.mbti}",
        )
    }

    private fun checkLoginValid() {
        val inputId = binding.edtLoginId.text.toString()
        val inputPwd = binding.edtLoginPwd.text.toString()

        if (inputId.isNotBlank() && inputPwd.isNotBlank()) {
            loginInfo = UserInfo(
                id = inputId,
                pwd = inputPwd,
                "",
                "",
            )
            viewModel.checkLoginValid(loginInfo, signUpInfo)
            observeLoginResult()
        }
    }

    private fun observeLoginResult() {
        viewModel.isLoginValid.observe(this) { result ->
            if (result) {
                binding.root.showSnackbar("로그인 성공!")
                intentToProfileActivity()
            } else {
                binding.root.showSnackbar("다시 로그인해주세요")
            }
        }
    }

    private fun intentToProfileActivity() {
        Intent(this, ProfileActivity::class.java).apply {
            putExtra(USER_INFO, signUpInfo)
            startActivity(this)
        }
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
