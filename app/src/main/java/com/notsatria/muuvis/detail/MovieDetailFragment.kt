package com.notsatria.muuvis.detail

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.ui.BaseFragment
import com.notsatria.core.utils.Constant
import com.notsatria.core.utils.formatStrToDate
import com.notsatria.core.utils.navigateUp
import com.notsatria.core.utils.onLoad
import com.notsatria.muuvis.R
import com.notsatria.muuvis.databinding.FragmentMovieDetailBinding
import com.notsatria.muuvis.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    //    private val viewModel: MovieDetailViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(300, TimeUnit.MILLISECONDS)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        val movie = arguments?.getParcelable("movie", Movie::class.java)
//        val isFromFavorite = arguments?.getBoolean("isFromFavorite", false)

        binding?.apply {
            ivPoster.transitionName = movie?.id.toString()
            ivPoster.onLoad("${Constant.POSTER_IMAGE_BASE_URL}${movie?.posterPath!!}")
            tvTitle.text = movie.title
            tvReleaseDate.text = getString(
                R.string.released_on,
                movie.releaseDate.formatStrToDate("yyyy-MM-dd", "dd MMMM yyyy")
            )
            tvRating.text = getString(
                R.string.vote_average_vote_count,
                movie.voteAverage.toString().subSequence(0, 3),
                movie.voteCount.toString()
            )
            tvOverview.text = movie.overview

            var favoriteStatus = movie.isFavorite
            setFavoriteStatus(favoriteStatus)

            ivBookmark.setOnClickListener {
                favoriteStatus = !favoriteStatus
                setFavoriteStatus(favoriteStatus)
                homeViewModel.setFavoriteMovie(movie, favoriteStatus)
                if (favoriteStatus) {
                    showToast(getString(R.string.movie_added_to_favorite))
                } else {
                    showToast(getString(R.string.movie_removed_from_favorite))
                }
            }

            icBack.setOnClickListener {
                navigateUp()
            }

            btnWatchNow.setOnClickListener {
                showToast("Coming soon...")
            }

            btnWatchTrailer.setOnClickListener {
                showToast("Coming soon...")
            }
        }
    }

    private fun FragmentMovieDetailBinding.setFavoriteStatus(isFavorite: Boolean) {
        val bookmarkIcon =
            if (isFavorite) R.drawable.ic_bookmark_filled_white_24dp else R.drawable.ic_bookmark_white_24dp
        ivBookmark.setImageDrawable(ContextCompat.getDrawable(requireContext(), bookmarkIcon))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}