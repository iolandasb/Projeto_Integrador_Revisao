package com.example.projetointegrador.data.repository

import android.net.Uri
import com.example.projetointegrador.data.model.*
import io.reactivex.Observable

class MoviesRepository {

    fun fetchList() : Observable<ListMovies> {
        return NetworkRetrofit.getService().getMovies()
    }

    fun fetchDetailsList(movieId : Int) : Observable<ReleaseDatesResponse> {
        return NetworkRetrofit.getService().getReleaseDate(movieId)
    }

    fun fetchRuntime(movieId5 : Int) : Observable<Runtime> {
        return NetworkRetrofit.getService().getRuntime(movieId5)
    }

    fun fetchCast(movieId2 : Int) : Observable<ListCast> {
        return NetworkRetrofit.getService().getCast(movieId2)
    }

    fun fetchAllGenres() : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getAllMoviesGenres()
    }

    fun fetchGenres(movieId3 : Int) : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getGenres(movieId3)
    }

    fun fetchSelectGenres(movieId4 : String) : Observable<ListMovies> {
        return NetworkRetrofit.getService().getSelectGenres(movieId4)
    }

    fun fetchSearch(movieSearched: Uri) : Observable<ListMovies> {
        return NetworkRetrofit.getService().searchForMovie(movieSearched)
    }

}