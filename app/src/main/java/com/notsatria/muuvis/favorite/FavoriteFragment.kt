package com.notsatria.muuvis.favorite

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.ui.BaseFragment
import com.notsatria.core.ui.MovieAdapterCallback
import com.notsatria.core.ui.MovieFavoriteAdapter
import com.notsatria.core.utils.navigateWithBundle
import com.notsatria.core.utils.visibleIf
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun inflateBinding(inflater: LayoutInflater): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            movieFavoriteAdapter = MovieFavoriteAdapter()

            setupRvFavorite()

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
                setupEmptyState(movies.isEmpty())
                movieFavoriteAdapter.submitList(movies)
            }
        }
    }

    private fun FragmentFavoriteBinding.setupRvFavorite() {
        rvFavorite.adapter = movieFavoriteAdapter
        rvFavorite.setHasFixedSize(true)
        rvFavorite.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        rvFavorite.doOnPreDraw {
            startPostponedEnterTransition()
        }

        movieFavoriteAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
//                    putBoolean("isFromFavorite", true)
                }
                navigateWithBundle(
                    R.id.action_navigation_favorite_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                favoriteViewModel.setFavoriteMovie(movie, !movie.isFavorite)
                if (!movie.isFavorite) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun FragmentFavoriteBinding.setupEmptyState(condition: Boolean) {
        includeEmptyState.apply {
            ivImage.setImageResource(R.drawable.il_empty_favorite)
            tvState.text = getString(R.string.no_favorite_movies_added_yet)
            root.visibleIf(condition)
        }
    }
}