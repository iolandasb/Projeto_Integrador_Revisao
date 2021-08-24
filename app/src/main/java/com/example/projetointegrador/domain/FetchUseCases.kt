package com.example.projetointegrador.domain

import android.net.Uri
import com.example.projetointegrador.data.repository.MoviesRepository

class FetchMoviesUseCase(private val repository: MoviesRepository = MoviesRepository()) {
    fun execute() = repository.fetchList()
}

class FetchReleaseDateUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val movieId: Int,
) {
    fun execute() = repository.fetchReleaseDate(movieId)
}

class FetchRuntimeUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val movieId: Int,
) {
    fun execute() = repository.fetchRuntime(movieId)
}

class FetchCastUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val movieId: Int,
) {
    fun execute() = repository.fetchCast(movieId)
}

class FetchAllGenresUseCase(private val repository: MoviesRepository = MoviesRepository()) {
    fun execute() = repository.fetchAllGenres()
}

class FetchGenresUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val movieId: Int,
) {
    fun execute() = repository.fetchGenres(movieId)
}

class FetchSelectGenresUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val genreId: List<Int>,
) {
    fun execute() = repository.fetchSelectGenres(genreId.joinToString(","))
}

class FetchSearchUseCase(
    private val repository: MoviesRepository = MoviesRepository(),
    private val movieSearched: Uri,
) {
    fun execute() = repository.fetchSearch(movieSearched)
}