package org.sopt.dosopttemplate.presentation.auth

import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.base.BaseActivity

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
    }
}
