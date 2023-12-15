package org.sopt.dosopttemplate.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.MainViewModel
import org.sopt.dosopttemplate.presentation.auth.LoginActivity
import org.sopt.dosopttemplate.presentation.base.BaseActivity
import org.sopt.dosopttemplate.presentation.main.home.HomeFragment
import org.sopt.dosopttemplate.presentation.main.mypage.MypageFragment

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentFromSignUp()
        initFragment()
        initBottomNavigation()
    }

    private fun getIntentFromSignUp() {
        viewModel.birthdayInfo.value = intent.getStringExtra(LoginActivity.BIRTHDAY) ?: ""
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
    }

    private fun initBottomNavigation() {
        binding.bnvHome.selectedItemId = R.id.menu_home
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_do_android -> {
                    replaceFragment(AndroidFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MypageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

    companion object {
        private const val BIRTHDAY = "birthday"

        fun getIntent(context: Context, birthday: String): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(BIRTHDAY, birthday)
            }
        }
    }
}
