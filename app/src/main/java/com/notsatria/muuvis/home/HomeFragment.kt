package com.notsatria.muuvis.home

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.core.domain.model.Movie
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
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: MovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()

        binding?.apply {
            homeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { result ->
                Log.i(TAG, "Result on view: $result ")
                when (result) {
                    is Resource.Loading -> {
                        tvNowPlaying.gone()
                        tvSeeAllNowPlaying.gone()
                        tvPopular.gone()
                        tvSeeAllPopular.gone()
                        tvTopRated.gone()
                        tvSeeAllTopRated.gone()
                        tvUpcoming.gone()
                        tvSeeAllUpcoming.gone()

                        includeHomeLoading.apply {
                            root.visible()
                            shimmer1.startShimmer()
                            shimmer2.startShimmer()
                            shimmer3.startShimmer()
                            shimmer4.startShimmer()
                        }
                    }

                    is Resource.Success -> {
                        tvNowPlaying.visible()
                        tvSeeAllNowPlaying.visible()
                        tvPopular.visible()
                        tvSeeAllPopular.visible()
                        tvTopRated.visible()
                        tvSeeAllTopRated.visible()
                        tvUpcoming.visible()
                        tvSeeAllUpcoming.visible()

                        includeHomeLoading.apply {
                            root.gone()
                            shimmer1.stopShimmer()
                            shimmer2.stopShimmer()
                            shimmer3.stopShimmer()
                            shimmer4.stopShimmer()
                        }
                        adapter.setItems(result.data!!)
                        Timber.tag(TAG).d("success: " + result.data)
                    }

                    is Resource.Error -> {
                        Timber.tag(TAG).d("error: " + result.message)
                    }

                }
            }
            setupRvNowPlaying()
            setupRvPopular()
            setupRvTopRated()
            setupRvUpcoming()

            icSearch.setOnClickListener {
                findNavController().navigate(
                    R.id.navigation_search, null, NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_home, false)
                        .build()
                )
            }
        }
    }

    private fun FragmentHomeBinding.setupRvNowPlaying() {
        rvNowPlaying.adapter = adapter
        rvNowPlaying.setHasFixedSize(true)

        adapter.callback = object : MovieAdapter.MovieAdapterCallback {
            override fun onItemClicked(movie: Movie) {
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(R.id.action_navigation_home_to_movieDetailFragment, bundle)
            }

            override fun onFavoriteClicked(movie: Movie) {
                homeViewModel.setFavoriteMovie(movie, !movie.isFavorite)
            }
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