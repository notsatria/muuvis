package com.notsatria.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.notsatria.core.R
import com.notsatria.core.databinding.ItemMoviePosterBinding
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.utils.Constant
import com.notsatria.core.utils.formatStrToDate
import com.notsatria.core.utils.onLoad

class MovieAdapter : BaseRecyclerViewAdapter<Movie, ItemMoviePosterBinding>(listOf()) {

    var callback: MovieAdapterCallback? = null

    override fun inflateBinding(parent: ViewGroup, viewType: Int): ItemMoviePosterBinding {
        return ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindItem(binding: ItemMoviePosterBinding, item: Movie, position: Int) {
        binding.apply {

            ivBookmark.setOnClickListener { callback?.onFavoriteClicked(item) }

            ivBookmark.setImageDrawable(
                if (item.isFavorite) ContextCompat.getDrawable(
                    root.context,
                    R.drawable.ic_bookmark_filled_white_24dp
                ) else ContextCompat.getDrawable(
                    root.context,
                    R.drawable.ic_bookmark_outlined_white_24dp
                )
            )

            tvTitle.text =
                root.context.getString(
                    R.string.title_release_year,
                    item.title,
                    item.releaseDate.formatStrToDate("yyyy-MM-dd", "yyyy")
                )
            tvRate.text = item.voteAverage.toString().subSequence(0, 3)
            ivPoster.onLoad("${Constant.POSTER_IMAGE_BASE_URL}${item.posterPath}")
            ivPoster.transitionName = item.id.toString()

            root.setOnClickListener { callback?.onItemClicked(item, ivPoster) }
        }
    }

    interface MovieAdapterCallback {
        fun onItemClicked(movie: Movie, ivPoster: ImageView)
        fun onFavoriteClicked(movie: Movie)
    }
}