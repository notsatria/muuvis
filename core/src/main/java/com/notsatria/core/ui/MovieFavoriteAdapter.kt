package com.notsatria.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.notsatria.core.R
import com.notsatria.core.databinding.ItemMovieLandscapeBinding
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.utils.Constant
import com.notsatria.core.utils.onLoad

class MovieFavoriteAdapter : BaseRecyclerViewAdapter<Movie, ItemMovieLandscapeBinding>(listOf()) {

    var callback: MovieAdapterCallback? = null

    override fun inflateBinding(parent: ViewGroup, viewType: Int): ItemMovieLandscapeBinding {
        return ItemMovieLandscapeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindItem(binding: ItemMovieLandscapeBinding, item: Movie, position: Int) {
        binding.apply {
            tvTitle.text = root.context.getString(
                R.string.title_release_year,
                item.title,
                item.releaseDate.subSequence(0, 4)
            )
            tvSubtitle.text = item.overview
            ivPoster.onLoad("${Constant.POSTER_IMAGE_BASE_URL}${item.posterPath}")
            ivPoster.transitionName = item.id.toString()

            if (item.isFavorite) {
                ivBookmark.setImageResource(R.drawable.ic_bookmark_filled_white_24dp)
            } else {
                ivBookmark.setImageResource(R.drawable.ic_bookmark_outlined_white_24dp)
            }

            root.setOnClickListener { callback?.onItemClicked(item, ivPoster) }
            ivBookmark.setOnClickListener { callback?.onFavoriteClicked(item) }
        }
    }
}