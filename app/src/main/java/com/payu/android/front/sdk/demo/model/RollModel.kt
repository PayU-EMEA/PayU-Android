package com.payu.android.front.sdk.demo.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class RollModel(
        @DrawableRes val drawableImage: Int,
        val rollPriceString: String,
        val rollPrice: Int,
        val namePrice: String
) : Parcelable
