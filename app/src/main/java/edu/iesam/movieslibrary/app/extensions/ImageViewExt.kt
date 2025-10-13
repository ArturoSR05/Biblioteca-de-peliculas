package edu.iesam.movieslibrary.app.extensions

import android.widget.ImageView
import coil.load

fun ImageView.loadUrl(url: String) {
    this.load(url)
}