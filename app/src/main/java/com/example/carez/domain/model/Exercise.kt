package com.example.carez.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise (
    val id: String = "",
    val name: String = "",
    val localPath: String? = null,
    val remoteUrl: String? = null,
    val isDefault: Boolean = false,
    val lvl: String? = null,
    val isDistanceType: Boolean = false,
    val metBeginner: Float = 0.0F,
    val metPro: Float = 0.0F
) : Parcelable
