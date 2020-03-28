package com.ocaterinca.ocaterinca.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun ImageView.imageUrl(image: String?) {
    Glide.with(this).load(image).apply(RequestOptions.circleCropTransform()).into(this)
}

@BindingAdapter("visibleGone")
fun View.setVisibleGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
