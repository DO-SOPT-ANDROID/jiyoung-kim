package org.sopt.dosopttemplate.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by activityViewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topAdapter = MyProfileAdapter(::setMyBirthday)
        val bottomAdapter = FriendProfileAdapter(::clickFriendsItem)
        initAdapter(topAdapter, bottomAdapter)
        viewModel.friendList.observe(viewLifecycleOwner) { newList ->
            bottomAdapter.submitList(newList)
        }
    }

    private fun initAdapter(topAdapter: MyProfileAdapter, bottomAdapter: FriendProfileAdapter) {
        bottomAdapter.submitList(viewModel.friendList.value)
        binding.rcvHomeMain.also {
            it.adapter = ConcatAdapter(topAdapter, bottomAdapter)
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setMyBirthday(): String = viewModel.user.value?.birthday.toString()

    private fun clickFriendsItem(position: Int) {
        val deleteBottomSheet = DeleteBottomsheetFragment.newInstance(position)
        deleteBottomSheet.show(parentFragmentManager, "show")
    }
}
