package com.reza.moviecatalogue20.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class MovieResponses(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("totalPages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsItem>,

    @field:SerializedName("totalResults")
    val totalResults: Int
)

data class ResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String



)

