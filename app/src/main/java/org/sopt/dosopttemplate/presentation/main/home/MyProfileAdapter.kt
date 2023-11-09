package org.sopt.dosopttemplate.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class MyProfileAdapter(private val myBirthDay: () -> String) :
    RecyclerView.Adapter<MyProfileAdapter.MyProfileViewHolder>() {

    class MyProfileViewHolder(
        private val binding: ItemMyProfileBinding,
        private val myBirthDay: () -> String,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            binding.tvMyprofileBirthday.text = myBirthDay.invoke() ?: ""
            Log.d("my", "myBirthDay:: $myBirthDay")
            Log.d("my", "binding.tvMyprofileBirthday.text:: ${binding.tvMyprofileBirthday.text}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        val binding =
            ItemMyProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProfileViewHolder(binding, myBirthDay)
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = 1
}
