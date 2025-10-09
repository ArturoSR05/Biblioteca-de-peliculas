package edu.iesam.movieslibrary.app.features.movies.data.local.remote

import edu.iesam.movieslibrary.app.features.movies.domain.Movie

fun MovieApiModel.toModel(): Movie {
    fun stripHtml(input: String?): String {
        return input?.replace(Regex("<.*?>"), "")?.trim().orEmpty()
    }
    return Movie(
        this.id,
        this.title,
        this.image?.medium ?: this.image?.original ?: "",
        summary = stripHtml(summary)
        )
}