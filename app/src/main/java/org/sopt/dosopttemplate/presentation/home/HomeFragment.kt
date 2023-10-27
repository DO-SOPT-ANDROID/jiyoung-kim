package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topAdapter = MyProfileAdapter()
        val bottomAdapter = FriendProfileAdapter()
        initAdapter(topAdapter, bottomAdapter)
    }

    private fun initAdapter(topAdapter: MyProfileAdapter, bottomAdapter: FriendProfileAdapter) {
        bottomAdapter.submitList(viewModel.friendList)
        binding.rcvHomeMain.also {
            it.adapter = ConcatAdapter(topAdapter, bottomAdapter)
            it.layoutManager = LinearLayoutManager(context)
        }
    }
}
