package com.reza.moviecatalogue20.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class MovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "title")
        var title:String = "-",

        @ColumnInfo(name = "yearRelease")
        var yearRelease:String = "-",

        @ColumnInfo(name = "poster")
        var poster:String="-",

        @ColumnInfo(name = "vote")
        var vote:Double=0.0,

        @ColumnInfo(name = "overview")
        var overview:String="-",

        @ColumnInfo(name = "favourite")
        var favourite:Boolean= false



        ): Parcelable