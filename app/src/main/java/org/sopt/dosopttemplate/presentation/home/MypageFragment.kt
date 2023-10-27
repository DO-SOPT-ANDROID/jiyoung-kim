package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentProfileBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity
import org.sopt.dosopttemplate.presentation.base.BaseFragment
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.loadImage
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        clickListener()
        observeWithdrawState()
    }

    private fun initView() {
        with(binding) {
            binding.ivProfileMain.loadImage(R.drawable.mike, 20f)
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
        viewModel.withdrawState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    Intent(requireContext(), LoginActivity::class.java)
                }

                is UiState.Failure -> {
                    binding.root.showSnackbar(getString(R.string.withdraw_failed))
                }
            }
        }
    }
}
