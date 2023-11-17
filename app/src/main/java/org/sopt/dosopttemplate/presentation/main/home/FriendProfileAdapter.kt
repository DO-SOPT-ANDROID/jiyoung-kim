package org.sopt.dosopttemplate.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding
import org.sopt.dosopttemplate.util.DiffCallback
import org.sopt.dosopttemplate.util.extension.loadImage

class FriendProfileAdapter(private val clickItem: (Int) -> Unit) :
    ListAdapter<FriendProfile, FriendProfileAdapter.FriendProfileViewHolder>(
        DiffCallback<FriendProfile>(
            onContentsTheSame = { old, new -> old.id == new.id },
            onItemsTheSame = { old, new -> old == new },
        ),
    ) {

    class FriendProfileViewHolder(
        private val binding: ItemFriendProfileBinding,
        private val clickItem: (Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FriendProfile) = with(binding) {
            ivFriendProfileMain.loadImage(data.image, 30f)
            tvFriendProfileName.text = data.name
            tvFriendProfileMessage.text = data.message
            clFriendProfileBackground.setOnClickListener {
                clickItem(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendProfileViewHolder {
        val binding =
            ItemFriendProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendProfileViewHolder(binding, clickItem)
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
