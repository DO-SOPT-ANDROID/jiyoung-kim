package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityProfileBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.loadImage
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        clickListener()
        observeWithdrawState()
    }

    private fun initView() {
        with(binding) {
            binding.ivProfileMain.loadImage(R.drawable.mike)
            tvProfileId.text = viewModel.user.value?.id.toString()
            tvProfileName.text = viewModel.user.value?.name.toString()
            tvProfileMbti.text = viewModel.user.value?.mbti.toString()
        }
    }

    private fun clickListener() {
        binding.tvProfileWithdraw.setOnClickListener {
            viewModel.deleteUser()
        }
    }

    private fun observeWithdrawState() {
        viewModel.withdrawState.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    Intent(this, LoginActivity::class.java)
                    finish()
                }

                is UiState.Failure -> {
                    binding.root.showSnackbar(getString(R.string.withdraw_failed))
                }
            }
        }
    }
}
