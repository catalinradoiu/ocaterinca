package com.ocaterinca.ocaterinca.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun ImageView.imageUrl(image: String?) {
    Glide.with(this).load(image).into(this)
}
