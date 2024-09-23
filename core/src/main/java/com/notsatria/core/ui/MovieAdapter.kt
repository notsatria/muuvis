package com.notsatria.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.notsatria.core.R
import com.notsatria.core.databinding.ItemMoviePosterBinding
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.utils.Constant
import com.notsatria.core.utils.formatStrToDate
import com.notsatria.core.utils.onLoad

class MovieAdapter : BaseRecyclerViewAdapter<Movie, ItemMoviePosterBinding>(listOf()) {

    var onItemClicked: ((Movie) -> Unit)? = null

    override fun inflateBinding(parent: ViewGroup, viewType: Int): ItemMoviePosterBinding {
        return ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindItem(binding: ItemMoviePosterBinding, item: Movie, position: Int) {
        binding.apply {
            root.setOnClickListener { onItemClicked?.invoke(item) }
            tvTitle.text =
                root.context.getString(
                    R.string.title_release_year,
                    item.title,
                    item.releaseDate.formatStrToDate("yyyy-MM-dd", "yyyy")
                )
            tvGenre.text = item.title
            ivPoster.onLoad("${Constant.POSTER_IMAGE_BASE_URL}${item.posterPath}")
        }
    }
}