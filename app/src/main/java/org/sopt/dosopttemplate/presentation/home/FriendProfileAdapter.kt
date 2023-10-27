package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding
import org.sopt.dosopttemplate.util.DiffCallback
import org.sopt.dosopttemplate.util.extension.loadImage

class FriendProfileAdapter :
    ListAdapter<FriendProfile, FriendProfileAdapter.FriendProfileViewHolder>(
        DiffCallback<FriendProfile>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new },
        ),
    ) {

    class FriendProfileViewHolder(private val binding: ItemFriendProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FriendProfile) = with(binding) {
            ivFriendProfileMain.loadImage(data.image, 30f)
            tvFriendProfileName.text = data.name
            tvFriendProfileMessage.text = data.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendProfileViewHolder {
        val binding =
            ItemFriendProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendProfileViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FriendProfileViewHolder,
        position: Int,
    ) {
        holder.onBind(
            getItem(position) as FriendProfile,
        )
    }
}
