package com.paulklauser.gesturedetector

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Gesture : Parcelable {
    @Parcelize
    object Tap : Gesture(), Parcelable
    @Parcelize
    object LongPress : Gesture(), Parcelable
}
