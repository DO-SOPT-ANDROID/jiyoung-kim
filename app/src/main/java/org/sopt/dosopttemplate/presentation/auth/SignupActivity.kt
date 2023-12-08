package org.sopt.dosopttemplate.presentation.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.EMPTY_ERROR
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.ID_ERROR
import org.sopt.dosopttemplate.presentation.auth.SignupViewModel.Companion.PWD_ERROR
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()
    private var formattedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        observeSignUpState()
        setErrorEditText()
        clickListeners()
        hideKeyboard()
    }

    private fun setErrorEditText() {
        viewModel.isIdValid.observe(this) { isIdValid ->
            Log.d("signUp", "isIdValid:: ${viewModel.isIdValid.value}")
            if (!isIdValid) {
                binding.tilSignupId.error = "영문/숫자 6-10글자가 포함되어야해요"
            } else {
                binding.tilSignupId.error = null
                binding.tilSignupId.isErrorEnabled = false
            }
        }
        viewModel.isPwdValid.observe(this) { isPwdValid ->
            Log.d("signUp", "isPwdValid:: ${viewModel.isPwdValid.value}")

            if (!isPwdValid) {
                binding.tilSignupPwd.error = "영문/숫자/특수문자 6-12글자가 포함되어야해요"
            } else {
                binding.tilSignupPwd.error = null
                binding.tilSignupPwd.isErrorEnabled = false
            }
        }
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
            viewModel.signUp()
        }

        binding.ivSignupCalendar.setOnClickListener {
            showCalendar()
        }
    }

    private fun intentToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            finish()
        }
    }

    private fun hideKeyboard() {
        binding.clSignupSecond.setOnClickListener {
            it.hideKeyboard()
        }
    }

    private fun showCalendar() {
        val datePicker = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            datePicker.set(Calendar.YEAR, year)
            datePicker.set(Calendar.MONTH, month)
            datePicker.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateBirthdayEditText(datePicker.time)
        }

        DatePickerDialog(
            this,
            dateListener,
            datePicker.get(Calendar.YEAR),
            datePicker.get(Calendar.MONTH),
            datePicker.get(Calendar.DAY_OF_MONTH),
        ).show()

        with(binding) {
            edtSignupBirthday.requestFocus()
            edtSignupBirthday.isCursorVisible = false
        }
    }

    private fun updateBirthdayEditText(date: Date) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        formattedDate = dateFormat.format(date)
        with(binding) {
            edtSignupBirthday.text = Editable.Factory.getInstance().newEditable(formattedDate)
            edtSignupBirthday.isCursorVisible = false
        }
    }
}
