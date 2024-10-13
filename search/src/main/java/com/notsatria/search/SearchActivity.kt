package com.notsatria.search

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.ui.MovieAdapterCallback
import com.notsatria.core.ui.MovieFavoriteAdapter
import com.notsatria.core.utils.visibleIf
import com.notsatria.muuvis.R
import com.notsatria.muuvis.di.SearchModuleDependencies
import com.notsatria.search.databinding.ActivitySearchBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels {
        factory
    }
    private lateinit var movieAdapter: MovieFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerSearchComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    SearchModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movieAdapter = MovieFavoriteAdapter()
        binding.apply {
            setupSearchView()
            setupRvSearchResult()
            searchMovies("a")

            icBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun ActivitySearchBinding.setupSearchView() {
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
        viewModel.searchMovies(query).observe(this) { movies ->
            binding.setupEmptyState(movies.isEmpty())
            movieAdapter.submitList(movies)
        }
    }

    private fun ActivitySearchBinding.setupRvSearchResult() {
        rvSearchResult.adapter = movieAdapter
        rvSearchResult.setHasFixedSize(true)
        rvSearchResult.layoutManager =
            LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)

        movieAdapter.callback = object : MovieAdapterCallback {
            override fun onItemClicked(movie: Movie, ivPoster: ImageView) {
                // Do nothing
            }

            override fun onFavoriteClicked(movie: Movie) {
                viewModel.setFavoriteMovie(movie, !movie.isFavorite)
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
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun ActivitySearchBinding.setupEmptyState(isEmpty: Boolean) {
        layoutEmpty.apply {
            visibleIf(isEmpty)
            tvState.text =
                getString(R.string.movie_not_found_please_input_another_keyword)
        }
    }
}