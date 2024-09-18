package com.notsatria.muuvis.core.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun ImageView.onLoadDrawable(drawable: Drawable, scaleType: RequestOptions) {
    Glide.with(this.context)
        .load(drawable)
        .apply(scaleType)
        .into(this)
}