package org.sopt.dosopttemplate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val birthdayInfo: MutableLiveData<String> = MutableLiveData("")
}
