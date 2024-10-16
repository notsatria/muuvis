package com.notsatria.muuvis.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.ui.MovieAdapter
import com.notsatria.core.ui.MovieAdapterCallback
import com.notsatria.core.utils.Resource
import com.notsatria.core.utils.navigateWithBundle
import com.notsatria.core.utils.visibleIf
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber.Forest.d

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)

        _binding = FragmentHomeBinding.inflate(inflater)

        nowPlayingAdapter = MovieAdapter()
        popularAdapter = MovieAdapter()
        topRatedAdapter = MovieAdapter()
        upcomingAdapter = MovieAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setupViews()
            setupRvNowPlaying()
            setupRvPopular()
            setupRvTopRated()
            setupRvUpcoming()

            observeMovies()

            postponeEnterTransition()
        }
    }

    private fun FragmentHomeBinding.setupViews() {
        tvSeeAllPopular.setOnClickListener {
            showToast(getString(R.string.coming_soon))
        }

        tvSeeAllNowPlaying.setOnClickListener {
            showToast(getString(R.string.coming_soon))
        }

        tvSeeAllTopRated.setOnClickListener {
            showToast(getString(R.string.coming_soon))
        }

        tvSeeAllUpcoming.setOnClickListener {
            showToast(getString(R.string.coming_soon))
        }

        icSearch.setOnClickListener {
            val uri = Uri.parse("muuvis://search")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun FragmentHomeBinding.setupRvNowPlaying() {
        rvNowPlaying.adapter = nowPlayingAdapter
        rvNowPlaying.setHasFixedSize(true)
        rvNowPlaying.doOnPreDraw {
            startPostponedEnterTransition()
        }

        nowPlayingAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(
                    R.id.action_navigation_home_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                homeViewModel.setFavoriteMovie(movie, !movie.isFavorite)
                if (!movie.isFavorite) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }
        }

        rvNowPlaying.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun FragmentHomeBinding.setupRvPopular() {
        rvPopular.adapter = popularAdapter
        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvPopular.doOnPreDraw {
            startPostponedEnterTransition()
        }

        popularAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(
                    R.id.action_navigation_home_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                homeViewModel.setFavoriteMovie(movie, !movie.isFavorite)
                if (!movie.isFavorite) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }
        }
    }

    private fun FragmentHomeBinding.setupRvTopRated() {
        rvTopRated.adapter = topRatedAdapter
        rvTopRated.setHasFixedSize(true)
        rvTopRated.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvTopRated.doOnPreDraw {
            startPostponedEnterTransition()
        }

        topRatedAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(
                    R.id.action_navigation_home_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                homeViewModel.setFavoriteMovie(movie, !movie.isFavorite)
                if (!movie.isFavorite) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }
        }
    }

    private fun FragmentHomeBinding.setupRvUpcoming() {
        rvUpcoming.adapter = upcomingAdapter
        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvUpcoming.doOnPreDraw {
            startPostponedEnterTransition()
        }

        upcomingAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(
                    R.id.action_navigation_home_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                homeViewModel.setFavoriteMovie(movie, !movie.isFavorite)
                if (!movie.isFavorite) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }
        }
    }

    private fun FragmentHomeBinding.observeMovies() {
        homeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading(true)
                    includeHomeLoading.apply {
                        shimmer1.startShimmer()
                    }
                }

                is Resource.Success -> {
                    showLoading(false)
                    nowPlayingAdapter.submitList(result.data!!)
                    includeHomeLoading.apply {
                        shimmer1.stopShimmer()
                    }
                    d("now playing result: " + result.data)
                }

                is Resource.Error -> {
                    d("now playing error: " + result.message)
                }

            }
        }

        homeViewModel.popularMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    includeHomeLoading.apply {
                        shimmer2.startShimmer()
                    }
                    d("popular movies loading")
                }

                is Resource.Success -> {
                    popularAdapter.submitList(result.data!!)
                    includeHomeLoading.apply {
                        shimmer2.stopShimmer()
                    }
                    d("popular movies success: ${result.data}")
                }

                is Resource.Error -> {
                    d("popular movies error: ${result.message}")
                }
            }
        }

        homeViewModel.topRatedMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    includeHomeLoading.apply {
                        shimmer3.startShimmer()
                    }
                    d("top rated movies loading")
                }

                is Resource.Success -> {
                    topRatedAdapter.submitList(result.data!!)
                    includeHomeLoading.apply {
                        shimmer3.stopShimmer()
                    }
                    d("top rated movies success: ${result.data}")
                }

                is Resource.Error -> {
                    d("top rated movies error: ${result.message}")
                }
            }

        }

        homeViewModel.upcomingMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    includeHomeLoading.apply {
                        shimmer4.startShimmer()
                    }
                    d("upcoming movies loading")
                }

                is Resource.Success -> {
                    upcomingAdapter.submitList(result.data!!)
                    includeHomeLoading.apply {
                        shimmer4.stopShimmer()
                    }
                    d("upcoming movies success: ${result.data}")
                }

                is Resource.Error -> {
                    d("upcoming movies error: ${result.message}")
                }
            }

        }
    }

    private fun FragmentHomeBinding.showLoading(isLoading: Boolean) {
        tvNowPlaying.visibleIf(!isLoading)
        tvSeeAllNowPlaying.visibleIf(!isLoading)
        tvPopular.visibleIf(!isLoading)
        tvSeeAllPopular.visibleIf(!isLoading)
        tvTopRated.visibleIf(!isLoading)
        tvSeeAllTopRated.visibleIf(!isLoading)
        tvUpcoming.visibleIf(!isLoading)
        tvSeeAllUpcoming.visibleIf(!isLoading)
        includeHomeLoading.root.visibleIf(isLoading)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            rvNowPlaying.adapter = null
            rvPopular.adapter = null
            rvTopRated.adapter = null
            rvUpcoming.adapter = null
        }
        _binding = null

    }
}