package com.example.carez.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.carez.R

@Suppress("DiscouragedApi")
fun ImageView.loadFoodImage(localPath: String?, remoteUrl: String?, placeholder: Int = R.drawable.img_comrang) {
    val context = this.context

    val resId = if (!localPath.isNullOrEmpty()) {
        context.resources.getIdentifier(
            localPath, // tên resource, ví dụ "img_banhmitrang"
            "drawable", // loại resource: drawable, layout, string, id, ...
            context.packageName // tên gói ứng dụng, ví dụ "com.example.carez"
        )
    } else 0

    when {
        resId != 0 -> {
            Glide.with(context)
                .load(resId)
                .placeholder(placeholder)
                .into(this)
        }

        !remoteUrl.isNullOrEmpty() -> {
            Glide.with(context)
                .load(remoteUrl)
                .placeholder(placeholder)
                .into(this)
        }

        else -> {
            this.setImageResource(placeholder)
        }
    }
}
