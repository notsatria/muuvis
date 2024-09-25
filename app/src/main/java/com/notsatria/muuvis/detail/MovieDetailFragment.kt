package com.notsatria.muuvis.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.ui.BaseFragment
import com.notsatria.core.utils.Constant
import com.notsatria.core.utils.Resource
import com.notsatria.core.utils.navigateUp
import com.notsatria.core.utils.onLoad
import com.notsatria.muuvis.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber.Forest.d

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        val movie = arguments?.getParcelable("movie", Movie::class.java)

        viewModel.getMovieDetail(movie?.id ?: 4).observe(viewLifecycleOwner) { resource ->
            when(resource) {
                is Resource.Loading -> {
                    d("Loading")
                }

                is Resource.Success -> {
                    d("Success: ${resource.data}")
                }

                is Resource.Error -> {
                    d("Error: ${resource.message}")
                }
            }
        }

        binding?.apply {
            ivPoster.onLoad("${Constant.POSTER_IMAGE_BASE_URL}${movie?.posterPath!!}")
            tvTitle.text = movie.title
            tvGenre.text = movie.genreIds.toString()
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.overview
            tvRating.text = movie.voteAverage.toString()

            icBack.setOnClickListener {
                navigateUp()
            }
        }
    }
}