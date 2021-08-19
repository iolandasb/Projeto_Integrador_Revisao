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

    fun fetchRuntime(movieId3 : Int) : Observable<Runtime> {
        return NetworkRetrofit.getService().getRuntime(movieId3)
    }

    fun fetchCast(movieId2 : Int) : Observable<ListCast> {
        return NetworkRetrofit.getService().getCast(movieId2)
    }

    fun fetchAllGenres() : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getAllMoviesGenres()
    }

    fun fetchGenres(movie_id4 : Int) : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getGenres(movie_id4)
    }

    fun fetchSelectGenres(genre_id : String) : Observable<ListMovies> {
        return NetworkRetrofit.getService().getSelectGenres(genre_id)
    }

    fun fetchSearch(movieSearched: Uri) : Observable<ListMovies> {
        return NetworkRetrofit.getService().searchForMovie(movieSearched)
    }

}