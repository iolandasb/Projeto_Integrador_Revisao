package com.example.projetointegrador.data.model

import android.net.Uri
import com.example.projetointegrador.data.repository.MoviesRepository

class FetchMoviesUseCase (private val repository: MoviesRepository = MoviesRepository())
{
    fun execute() = repository.fetchList()
}

class FetchDetailsUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId: Int)
{
    fun execute() = repository.fetchDetailsList(movieId)
}

class FetchCastUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId2: Int)
{
    fun execute() = repository.fetchCast(movieId2)
}

class FetchAllGenresUseCase(private val repository: MoviesRepository = MoviesRepository())
{
    fun execute() = repository.fetchAllGenres()
}

class FetchGenresUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId3: Int)
{
    fun execute() = repository.fetchGenres(movieId3)
}

class FetchSelectGenresUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId4 : List<Int>)
{
    fun execute() = repository.fetchSelectGenres(movieId4.joinToString(","))
}

class FetchRuntimeUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieId5 : Int)
{
    fun execute() = repository.fetchRuntime(movieId5)
}

class FetchSearchUseCase(private val repository: MoviesRepository = MoviesRepository(), private val movieSearched: Uri)
{
    fun execute() = repository.fetchSearch(movieSearched)
}