package com.example.projetointegrador.data.repository

import android.net.Uri
import com.example.projetointegrador.data.model.*
import io.reactivex.Observable

class MoviesRepository {

    fun fetchList() : Observable<ListMovies> {
        return NetworkRetrofit.getService().getMovies()
    }

    fun fetchReleaseDate(movieId : Int) : Observable<ReleaseDatesResponse> {
        return NetworkRetrofit.getService().getReleaseDate(movieId)
    }

    fun fetchRuntime(movieId : Int) : Observable<Runtime> {
        return NetworkRetrofit.getService().getRuntime(movieId)
    }

    fun fetchCast(movieId : Int) : Observable<ListCast> {
        return NetworkRetrofit.getService().getCast(movieId)
    }

    fun fetchAllGenres() : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getAllMoviesGenres()
    }

    fun fetchGenres(movieId : Int) : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getGenres(movieId)
    }

    fun fetchSelectGenres(genreId : String) : Observable<ListMovies> {
        return NetworkRetrofit.getService().getSelectGenres(genreId)
    }

    fun fetchSearch(movieSearched: Uri) : Observable<ListMovies> {
        return NetworkRetrofit.getService().searchForMovie(movieSearched)
    }

}