package com.example.themovieapp.common.extension


import android.widget.ImageView
import coil.load
import com.example.themovieapp.R

fun ImageView.loadImageView(imageUrl: String?) {
    val context = this.context
    this.load(imageUrl) {
        crossfade(600)
        error(R.drawable.ic_placeholder)
    }
}
