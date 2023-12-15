package org.sopt.dosopttemplate.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    val user = MutableLiveData<User>()
    var friendList: MutableLiveData<List<FriendProfile>> = MutableLiveData<List<FriendProfile>>()

    init {
        friendList.value = listOf(
            FriendProfile(
                id = 1,
                image = R.drawable.img_1,
                name = "배지현",
                message = "케로로 공주",
            ),
            FriendProfile(
                id = 2,
                image = R.drawable.img_2,
                name = "박소현",
                message = "소로로 공주",
            ),
            FriendProfile(
                id = 3,
                image = R.drawable.img_3,
                name = "서재원",
                message = "안드의 서영석",
            ),
            FriendProfile(
                id = 4,
                image = R.drawable.img_4,
                name = "곽의진",
                message = "곽폼미",
            ),
            FriendProfile(
                id = 5,
                image = R.drawable.img_5,
                name = "이삭",
                message = "토스트",
            ),
            FriendProfile(
                id = 6,
                image = R.drawable.img_6,
                name = "우상욱",
                message = "역곡 대마왕",
            ),
            FriendProfile(
                id = 7,
                image = R.drawable.img_7,
                name = "박채은",
                message = "우리 점심 언제 같이 먹어?",
            ),
            FriendProfile(
                id = 8,
                image = R.drawable.img_8,
                name = "김언지",
                message = "어니언지",
            ),
            FriendProfile(
                id = 9,
                image = R.drawable.img_9,
                name = "이태희",
                message = "최고의 안팟장",
            ),
            FriendProfile(
                id = 10,
                image = R.drawable.img_10,
                name = "김상호",
                message = "술 좀 그만 마셔",
            ),
            FriendProfile(
                id = 11,
                image = R.drawable.img_11,
                name = "김민정",
                message = "똑순이 갓기 최고",
            ),
            FriendProfile(
                id = 12,
                image = R.drawable.img_12,
                name = "박강희",
                message = "등산가야하는데..",
            ),
        )
    }

    fun removeFriendAt(position: Int) {
        friendList.value?.let {
            val updatedList = it.toMutableList().apply {
                removeAt(position)
            }
            friendList.value = updatedList
        }
    }
}
