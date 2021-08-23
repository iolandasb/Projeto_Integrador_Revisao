package com.example.projetointegrador.data.repository

import android.net.Uri
import com.example.projetointegrador.data.model.*
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    fun getMovies(): Observable<ListMovies>

    @GET("movie/{movie_id}/release_dates")
    fun getReleaseDate(@Path("movie_id") movieId: Int) : Observable<ReleaseDatesResponse>

    @GET("movie/{movie_id}")
    fun getRuntime(@Path("movie_id") movieId: Int): Observable<Runtime>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movieId: Int): Observable<ListCast>

    @GET("genre/movie/list")
    fun getAllMoviesGenres(): Observable<ListAllMoviesGenres>

    @GET("movie/{movie_id}")
    fun getGenres(@Path("movie_id") movieId: Int): Observable<ListAllMoviesGenres>

    @GET("discover/movie")
    fun getSelectGenres(@Query("with_genres") genreId: String): Observable<ListMovies>

    @GET("search/movie")
    fun searchForMovie(@Query("query") movieSearched: Uri): Observable<ListMovies>

}