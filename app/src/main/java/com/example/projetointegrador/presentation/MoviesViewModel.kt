package com.example.projetointegrador.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetointegrador.data.model.*
import com.example.projetointegrador.data.repository.Movies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(private val error: ErrorListener? = null) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())
    val moviesLiveData: LiveData<MutableList<Infos>> = _moviesLiveData

    private val _releaseDateLiveData = MutableLiveData<ReleaseDatesResponse>()
    val releaseDateLiveData: LiveData<ReleaseDatesResponse> = _releaseDateLiveData

    private val _runtimeLiveData = MutableLiveData<Runtime>()
    val runtimeLiveData: LiveData<Runtime> = _runtimeLiveData

    private val _castLiveData = MutableLiveData<List<InfosCast>>()
    val castLiveData: LiveData<List<InfosCast>> = _castLiveData

    private val _allGenresLiveData = MutableLiveData<List<AllMoviesGenres>>()
    val allGenresLiveData: LiveData<List<AllMoviesGenres>> = _allGenresLiveData

    private val _searchLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())
    val searchLiveData: LiveData<MutableList<Infos>> = _searchLiveData

    val _favoriteMoviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())

    var favorites = Movies()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("CheckResult")
    fun getInfos() {
        val fetchMoviesUseCase = FetchMoviesUseCase()
        fetchMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                favorites.setList(it.results)
                _moviesLiveData.value = favorites.listFavoritesMovies()
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getMoviesReleaseDate(movieId : Int) {
        val fetchDetailsUseCase = FetchReleaseDateUseCase(movieId = movieId)
        fetchDetailsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _releaseDateLiveData.value = it
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getMoviesRuntime(movieId3 : Int) {
        val fetchRuntimeUseCase = FetchRuntimeUseCase(movieId3 = movieId3)
        fetchRuntimeUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _runtimeLiveData.value = it
            },{
                error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getCastInfos(movieId2 : Int) {
        val fetchCastUseCase = FetchCastUseCase(movieId2 = movieId2)
        fetchCastUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _castLiveData.value = it.cast
            },{
            error?.pageError()
            })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("CheckResult")
    fun getAllGenresInfos() {
        val fetchAllGenresCastUseCase = FetchAllGenresUseCase()
        fetchAllGenresCastUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _allGenresLiveData.value = it.genres
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getGenresInfos(movie_id4: Int) {
        val fetchGenresCastUseCase = FetchGenresUseCase(movie_id4 = movie_id4)
        fetchGenresCastUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _allGenresLiveData.value = it.genres
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getGenresSelect(genre_id : List<Int>) {
        val fetchSelectGenresUseCase = FetchSelectGenresUseCase(genre_id = genre_id)
        fetchSelectGenresUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _moviesLiveData.value = it.results
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getSearch(movieSearched: Uri) {
        val fetchSearchUseCase = FetchSearchUseCase(movieSearched = movieSearched)
        fetchSearchUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _searchLiveData.value = it.results
            },{
            error?.pageError()
            })
    }

    fun getGenresSearch(genre_ids: List<Int>) {
        val searchGenres = _searchLiveData.value
            ?.filter { it.genre_ids.containsAll(genre_ids) }
            ?.toMutableList()
        _searchLiveData.value = searchGenres
    }

    fun getGenresFavorites(genre_ids: List<Int>) {
        val favoritesMovies = favorites.listFavoritesMovies().filter { it.favoriteCheck }
            .filter { it.genre_ids.containsAll(genre_ids) }
            .toMutableList()
        _favoriteMoviesLiveData.value = favoritesMovies
    }

    fun getFavoriteMovies() {
        val favoritesData = favorites.listFavoritesMovies().filter { it.favoriteCheck }
        _favoriteMoviesLiveData.value = favoritesData as MutableList<Infos>
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addFavorite(movie: Infos) {
        favorites.addToFavorites(movie)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

    fun removeFavorite(movieId: Infos) {
        favorites.removeFromFavorites(movieId)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

}