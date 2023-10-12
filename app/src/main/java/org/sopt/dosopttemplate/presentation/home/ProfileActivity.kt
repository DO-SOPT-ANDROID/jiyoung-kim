package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.util.Log
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityProfileBinding
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.util.extension.loadImage

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUSerInfo()
        loadImage()
    }

    private fun getUSerInfo() {
        userInfo = intent.getParcelableExtra(USER_INFO) ?: UserInfo("null", "null", "null", "null")
        Log.d("test", "userInfo:: $userInfo")
        binding.tvProfileId.text = userInfo.id
        binding.tvProfileName.text = userInfo.name
        binding.tvProfileMbti.text = userInfo.mbti
    }

    private fun loadImage() {
        binding.ivProfileMain.loadImage(R.drawable.mike)
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
