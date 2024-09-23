package com.notsatria.muuvis.home

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.core.ui.BaseFragment
import com.notsatria.core.ui.MovieAdapter
import com.notsatria.core.utils.Resource
import com.notsatria.core.utils.gone
import com.notsatria.core.utils.navigateById
import com.notsatria.core.utils.navigateWithBundle
import com.notsatria.core.utils.visible
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: MovieAdapter
    private val homeViewMovie: HomeViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()

        binding?.apply {
            homeViewMovie.nowPlayingMovies.observe(viewLifecycleOwner) { result ->
                Log.i(TAG, "Result on view: $result ")
                when (result) {
                    is Resource.Loading -> {
                        includeHomeLoading.apply {
                            root.visible()
                            shimmer1.startShimmer()
                            shimmer2.startShimmer()
                        }
                    }

                    is Resource.Success -> {
                        includeHomeLoading.apply {
                            root.gone()
                            shimmer1.stopShimmer()
                            shimmer2.stopShimmer()
                        }
                        adapter.setItems(result.data!!)
                        d(TAG, "success: ${result.data}")
                    }

                    is Resource.Error -> {
                        d(TAG, "error: ${result.message}")
                    }

                }
            }
            setupRvNowPlaying()
            setupRvPopular()
            setupRvTopRated()
            setupRvUpcoming()

            icSearch.setOnClickListener {
                navigateById(R.id.navigation_search)
            }
        }
    }

    private fun FragmentHomeBinding.setupRvNowPlaying() {
        rvNowPlaying.adapter = adapter
        rvNowPlaying.setHasFixedSize(true)

        adapter.onItemClicked = { movie ->
            val bundle = Bundle().apply {
                putParcelable("movie", movie)
            }
            navigateWithBundle(R.id.action_navigation_home_to_movieDetailFragment, bundle)
        }

        rvNowPlaying.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun FragmentHomeBinding.setupRvPopular() {
        rvPopular.adapter = adapter
        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun FragmentHomeBinding.setupRvTopRated() {
        rvTopRated.adapter = adapter
        rvTopRated.setHasFixedSize(true)
        rvTopRated.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun FragmentHomeBinding.setupRvUpcoming() {
        rvUpcoming.adapter = adapter
        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}