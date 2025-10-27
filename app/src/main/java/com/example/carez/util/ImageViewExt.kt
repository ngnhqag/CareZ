package com.example.carez.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.carez.R

@Suppress("DiscouragedApi")
fun ImageView.loadFoodImage(
    localPath: String?,
    remoteUrl: String?,
    placeholder: Int = R.drawable.img_comrang
) {
    val context = this.context
    Glide.with(context).clear(this)

    val resId = if (!localPath.isNullOrEmpty()) {
        context.resources.getIdentifier(localPath, "drawable", context.packageName)
    } else 0

    val glideRequest = when {
        resId != 0 -> Glide.with(context).load(resId)
        !remoteUrl.isNullOrEmpty() -> Glide.with(context).load(remoteUrl)
        else -> Glide.with(context).load(placeholder)
    }

    glideRequest
        .placeholder(placeholder)
        .dontTransform()
        .into(this)

}
