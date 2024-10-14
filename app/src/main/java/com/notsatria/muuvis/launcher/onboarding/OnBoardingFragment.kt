package com.notsatria.muuvis.launcher.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.notsatria.core.utils.visibleIf
import com.notsatria.muuvis.MainActivity
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private val viewModel: OnBoardingViewModel by viewModels()

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
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

                btnLogin.setOnClickListener {
                    viewModel.setUserIsLoggedIn(true)
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}