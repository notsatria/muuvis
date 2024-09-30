package com.notsatria.muuvis.launcher

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.notsatria.muuvis.MainActivity
import com.notsatria.muuvis.databinding.ActivityLauncherBinding
import com.notsatria.muuvis.launcher.onboarding.LauncherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val isLoggedIn = viewModel.getUserLoginStatus().first()
            if (isLoggedIn) {
                startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}