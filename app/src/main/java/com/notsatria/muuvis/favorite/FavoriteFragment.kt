package com.notsatria.muuvis.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.notsatria.core.ui.BaseFragment
import com.notsatria.muuvis.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            // TODO
        }
    }
}