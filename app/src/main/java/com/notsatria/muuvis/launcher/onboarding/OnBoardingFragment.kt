package com.notsatria.muuvis.launcher.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.notsatria.muuvis.MainActivity
import com.notsatria.muuvis.R
import com.notsatria.muuvis.core.ui.BaseFragment
import com.notsatria.muuvis.core.utils.visibleIf
import com.notsatria.muuvis.databinding.FragmentOnboardingBinding

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            setupViewPager()
        }
    }

    private fun FragmentOnboardingBinding.setupViewPager() {
        val adapter = OnBoardingAdapter()
        vpOnboarding.adapter = adapter

        indicator.attachTo(vpOnboarding)

        vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnLoginFull.visibleIf(position == 2)
                llButton.visibleIf(position < 2)

                var title = ""
                var subtitle = ""

                when (position) {
                    0 -> {
                        title = getString(R.string.title_onboarding_1)
                        subtitle = getString(R.string.subtitle_onboarding_1)
                        btnNext.setOnClickListener {
                            vpOnboarding.setCurrentItem(1, true)
                        }
                    }

                    1 -> {
                        title = getString(R.string.title_onboarding_2)
                        subtitle = getString(R.string.subtitle_onboarding_2)
                        btnNext.setOnClickListener {
                            vpOnboarding.setCurrentItem(2, true)
                        }
                    }

                    2 -> {
                        title = getString(R.string.title_onboarding_3)
                        subtitle = getString(R.string.subtitle_onboarding_3)
                        btnLoginFull.setOnClickListener {
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                        }

                    }

                }
                tvTitle.text = title
                tvSubtitle.text = subtitle
            }
        })

    }
}