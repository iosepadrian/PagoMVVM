package com.example.pagoappmvvm.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(this)
            .load(url)
            .into(this)
    } else {
        setImageDrawable(null)
    }
}