package com.notsatria.muuvis.launcher.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.ItemOnboardingBinding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    private val imageList = listOf(
        R.drawable.il_onboarding_1,
        R.drawable.il_onboarding_2,
        R.drawable.il_onboarding_3
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingAdapter.ViewHolder {
        return ViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            ivOnboarding.setImageResource(imageList[position])
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ViewHolder(val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)
}