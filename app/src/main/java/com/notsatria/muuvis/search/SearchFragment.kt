package com.notsatria.muuvis.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
import com.notsatria.muuvis.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var movieAdapter: MovieFavoriteAdapter
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        movieAdapter = MovieFavoriteAdapter()
        binding?.apply {
            setupSearchView()
            setupRvSearchResult()
            searchMovies("a")
        }
    }

    private fun FragmentSearchBinding.setupSearchView() {
        searchView.setupWithSearchBar(searchBar)
        searchView.editText.setOnEditorActionListener { _, _, _ ->
            val query = searchView.text.toString().trim()

            searchMovies(query)

            searchBar.setText(searchView.text)
            searchView.hide()
            false
        }
    }

    private fun searchMovies(query: String) {
        searchViewModel.searchMovies(query).observe(viewLifecycleOwner) { movies ->
            setupEmptyState(movies.isEmpty())
            movieAdapter.submitList(movies)
        }
    }

    private fun FragmentSearchBinding.setupRvSearchResult() {
        rvSearchResult.adapter = movieAdapter
        rvSearchResult.setHasFixedSize(true)
        rvSearchResult.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        movieAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                val extras = FragmentNavigatorExtras(ivPoster to movie.id.toString())
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                navigateWithBundle(
                    R.id.action_navigation_search_to_movieDetailFragment,
                    bundle,
                    extras
                )
            }

            override fun onFavoriteClicked(movie: Movie) {
                searchViewModel.setFavoriteMovie(movie, !movie.isFavorite)
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

    private fun setupEmptyState(isEmpty: Boolean) {
        binding?.includeEmptyState?.apply {
            root.visibleIf(isEmpty)
            tvState.text =
                getString(R.string.movie_not_found_please_input_another_keyword)
        }
    }
}