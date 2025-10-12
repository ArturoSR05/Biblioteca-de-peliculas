package edu.iesam.movieslibrary.app.features.movies.data.local.db

import edu.iesam.movieslibrary.app.features.movies.domain.Movie

fun Movie.toEntity(): MovieEntity = MovieEntity(
    this.id,
    this.title,
    image = this.image,
    this.summary
)

fun MovieEntity.toDomain(): Movie = Movie(
    this.id,
    this.tittle,
    this.image,
    summary = this.summary
)