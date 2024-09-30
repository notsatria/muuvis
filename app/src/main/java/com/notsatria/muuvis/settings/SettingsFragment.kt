package com.notsatria.muuvis.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.notsatria.core.ui.BaseFragment
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentSettingsBinding
import com.notsatria.muuvis.launcher.LauncherActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnLogout.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.logout))
                    .setMessage(getString(R.string.do_you_want_to_logout_from_muuvis))
                    .setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.setUserIsLoggedIn(false)
                        startActivity(Intent(requireActivity(), LauncherActivity::class.java))
                        requireActivity().finish()
                    }
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}