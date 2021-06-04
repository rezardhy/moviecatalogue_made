package com.reza.moviecatalogue20.core.data.utils

import com.reza.moviecatalogue20.core.data.domain.Movie
import com.reza.moviecatalogue20.core.data.source.local.entity.MovieEntity
import com.reza.moviecatalogue20.core.data.source.remote.response.ResultsItem

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()

        input.map{
            val movies = MovieEntity(
                    id = it.id,
                    title = it.title,
                    yearRelease = it.release_date,
                    poster = it.poster_path,
                    vote = it.voteAverage,
                    overview = it.overview

            )
            list.add(movies)
        }

        return list
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        year = it.yearRelease,
                        img = it.poster,
                        overview = it.overview,
                        vote = it.vote,
                        favourite = it.favourite
                )
            }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
            id = input.id!!,
            title = input.title!!,
            yearRelease = input.year!!,
            poster = input.img!!,
            vote = input.vote,
            overview = input.overview,
            favourite = input.favourite



    )

}