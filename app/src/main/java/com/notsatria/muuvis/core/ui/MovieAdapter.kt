package com.notsatria.muuvis.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.RequestOptions
import com.notsatria.muuvis.R
import com.notsatria.muuvis.core.utils.onLoadDrawable
import com.notsatria.muuvis.databinding.ItemMoviePosterBinding

class MovieAdapter : BaseRecyclerViewAdapter<Movie, ItemMoviePosterBinding>(listOf()) {
    override fun inflateBinding(parent: ViewGroup, viewType: Int): ItemMoviePosterBinding {
        return ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindItem(binding: ItemMoviePosterBinding, item: Movie, position: Int) {
        binding.apply {
            tvTitle.text =
                root.context.getString(R.string.title_release_year, item.title, item.releaseYear)
            tvGenre.text = item.genre
            ContextCompat.getDrawable(ivPoster.context, item.posterImage)
                ?.let { ivPoster.onLoadDrawable(it, RequestOptions()) }
        }
    }
}

data class Movie(
    val title: String,
    val genre: String,
    val posterImage: Int,
    val releaseYear: String,
)