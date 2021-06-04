package com.reza.moviecatalogue20.core.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
        var id: Int? =0,
        var title:String? = "",
        var year:String? = "",
        var img:String? = "",
        var vote:Double = 0.0,
        var overview:String = "",
        var favourite:Boolean = false
):Parcelable