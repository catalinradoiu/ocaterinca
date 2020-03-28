package com.ocaterinca.ocaterinca.utils

import android.animation.ValueAnimator
import android.view.View
import android.widget.ImageView
import androidx.annotation.DimenRes
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

fun View.resizeAnim(size: Float) {
    if (width == 0 || height == 0) {
        return
    }
    if (width == size.toInt() && height == size.toInt()) {
        return
    }
    val slideAnimator = ValueAnimator
        .ofInt(width, size.toInt())
        .setDuration(500)
    slideAnimator.addUpdateListener {
        val value = it.animatedValue as Int
        val newParams = layoutParams
        newParams.width = value
        newParams.height = value
        layoutParams = newParams
        requestLayout()
    }
    slideAnimator.start()
}

fun View.getDim(@DimenRes res: Int): Float {
    return resources.getDimension(res)
}