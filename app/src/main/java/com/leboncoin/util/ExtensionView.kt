package com.leboncoin.util

import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.loadPicture(url: String) {
    Picasso.get().load(url).centerCrop().resize(150, 150)
        .placeholder(android.R.drawable.ic_dialog_alert).into(this)
}
