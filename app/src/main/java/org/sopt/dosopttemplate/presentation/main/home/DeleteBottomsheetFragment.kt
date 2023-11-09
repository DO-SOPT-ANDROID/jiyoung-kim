package org.sopt.dosopttemplate.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentDeleteBottomsheetBinding
import org.sopt.dosopttemplate.presentation.base.BaseBottomsheetFragment

class DeleteBottomsheetFragment :
    BaseBottomsheetFragment<FragmentDeleteBottomsheetBinding>(R.layout.fragment_delete_bottomsheet) {
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_POSITION) ?: -1
        clickListener(position)
    }

    private fun clickListener(position: Int) {
        binding.clDeleteBackground.setOnClickListener {
            viewModel.removeFriendAt(position)
            dismiss()
        }
    }

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): DeleteBottomsheetFragment {
            val fragment = DeleteBottomsheetFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
}
