package com.notsatria.core.utils

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.Locale

// View extensions

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

// Glide extensions

fun ImageView.onLoadDrawable(drawable: Drawable, scaleType: RequestOptions) {
    Glide.with(this.context)
        .load(drawable)
        .apply(scaleType)
        .into(this)
}

fun ImageView.onLoad(path: String) {
    if (path != "") {
        Glide.with(this.context)
            .load(path)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    } else {
        this.setImageDrawable(null)
    }
}

// Navigation extensions

fun Fragment.navigateWithBundle(@IdRes resId: Int, bundle: Bundle) {
    this.findNavController().navigate(resId, bundle)
}

fun Fragment.navigateById(@IdRes resId: Int) {
    this.findNavController().navigate(resId)
}

fun Fragment.navigateUp() {
    this.findNavController().navigateUp()
}

// Date formatter

fun String.formatStrToDate(inputFormat: String, outputFormat: String): String {
    val sdf = SimpleDateFormat(inputFormat, Locale.getDefault())
    val date = sdf.parse(this)
    val newSdf = SimpleDateFormat(outputFormat, Locale.getDefault())
    return newSdf.format(date!!)
}