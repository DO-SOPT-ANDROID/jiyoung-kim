package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class MyProfileAdapter : RecyclerView.Adapter<MyProfileAdapter.MyProfileViewHolder>() {

    class MyProfileViewHolder(private val binding: ItemMyProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        val binding =
            ItemMyProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
