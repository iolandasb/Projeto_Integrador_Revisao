package com.example.projetointegrador.data.model

import android.net.Uri
import com.example.projetointegrador.data.repository.MoviesRepository

class FetchMoviesUseCase (private val repository: MoviesRepository = MoviesRepository())
{
    fun execute() = repository.fetchList()
}

class FetchReleaseDateUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId: Int)
{
    fun execute() = repository.fetchReleaseDate(movieId)
}

class FetchRuntimeUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId3 : Int)
{
    fun execute() = repository.fetchRuntime(movieId3)
}

class FetchCastUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId2: Int)
{
    fun execute() = repository.fetchCast(movieId2)
}

class FetchAllGenresUseCase(private val repository: MoviesRepository = MoviesRepository())
{
    fun execute() = repository.fetchAllGenres()
}

class FetchGenresUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movie_id4: Int)
{
    fun execute() = repository.fetchGenres(movie_id4)
}

class FetchSelectGenresUseCase(private val repository: MoviesRepository = MoviesRepository(), private val genre_id : List<Int>)
{
    fun execute() = repository.fetchSelectGenres(genre_id.joinToString(","))
}

class FetchSearchUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieSearched: Uri)
{
    fun execute() = repository.fetchSearch(movieSearched)
}