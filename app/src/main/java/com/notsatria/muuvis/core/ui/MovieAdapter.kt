package com.notsatria.muuvis.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.RequestOptions
import com.notsatria.muuvis.R
import com.notsatria.muuvis.core.domain.model.Movie
import com.notsatria.muuvis.core.utils.onLoadDrawable
import com.notsatria.muuvis.databinding.ItemMoviePosterBinding

class MovieAdapter : BaseRecyclerViewAdapter<Movie, ItemMoviePosterBinding>(listOf()) {
    override fun inflateBinding(parent: ViewGroup, viewType: Int): ItemMoviePosterBinding {
        return ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindItem(binding: ItemMoviePosterBinding, item: Movie, position: Int) {
        binding.apply {
            tvTitle.text =
                root.context.getString(R.string.title_release_year, item.title, item.releaseDate)
            tvGenre.text = item.title
            ContextCompat.getDrawable(ivPoster.context, R.drawable.il_onboarding_1)
                ?.let { ivPoster.onLoadDrawable(it, RequestOptions()) }
        }
    }

    fun swapData(newList: List<Movie>) {
        this.setItems(newList)
        notifyDataSetChanged()
    }
}