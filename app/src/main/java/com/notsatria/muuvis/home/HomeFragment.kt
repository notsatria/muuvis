package com.notsatria.muuvis.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.notsatria.muuvis.R
import com.notsatria.muuvis.core.ui.BaseFragment
import com.notsatria.muuvis.core.ui.Movie
import com.notsatria.muuvis.core.ui.MovieAdapter
import com.notsatria.muuvis.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: MovieAdapter
    private val movieList: List<Movie> = listOf(
        Movie(
            title = "Batman",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_1,
            releaseYear = "2022"
        ),
        Movie(
            title = "Spiderman",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_2,
            releaseYear = "2022"
        ),
        Movie(
            title = "Ironman",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_3,
            releaseYear = "2022"
        ),
        Movie(
            title = "Captain America",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_1,
            releaseYear = "2022"
        ),
        Movie(
            title = "Thor",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_2,
            releaseYear = "2022"
        ),
        Movie(
            title = "Black Panther",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_3,
            releaseYear = "2022"
        ),
        Movie(
            title = "Doctor Strange",
            genre = "Action",
            posterImage = R.drawable.il_onboarding_1,
            releaseYear = "2022"
        ),
    )

    override fun inflateBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()
        adapter.setItems(movieList)
        binding?.apply {
            setupRvNowPlaying()
            setupRvPopular()
            setupRvTopRated()
            setupRvUpcoming()
        }
    }

    private fun FragmentHomeBinding.setupRvNowPlaying() {
        rvNowPlaying.adapter = adapter
        rvNowPlaying.setHasFixedSize(true)
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
}