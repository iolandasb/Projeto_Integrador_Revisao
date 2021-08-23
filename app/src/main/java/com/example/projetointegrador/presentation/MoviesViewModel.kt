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
import com.example.projetointegrador.presentation.error.ErrorListener
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
    fun getMoviesRuntime(movieId : Int) {
        val fetchRuntimeUseCase = FetchRuntimeUseCase(movieId = movieId)
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
    fun getCastInfos(movieId : Int) {
        val fetchCastUseCase = FetchCastUseCase(movieId = movieId)
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
    fun getGenresInfos(movieId: Int) {
        val fetchGenresCastUseCase = FetchGenresUseCase(movieId = movieId)
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
    fun getGenresSelect(genreId : List<Int>) {
        val fetchSelectGenresUseCase = FetchSelectGenresUseCase(genreId = genreId)
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

    fun getGenresSearch(genreId: List<Int>) {
        val searchGenres = _searchLiveData.value
            ?.filter { it.genre_ids.containsAll(genreId) }
            ?.toMutableList()
        _searchLiveData.value = searchGenres
    }

    fun getGenresFavorites(genreId: List<Int>) {
        val favoritesMovies = favorites.listFavoritesMovies().filter { it.favoriteCheck }
            .filter { it.genre_ids.containsAll(genreId) }
            .toMutableList()
        _favoriteMoviesLiveData.value = favoritesMovies
    }

    fun getFavoriteMovies() {
        val favoritesData = favorites.listFavoritesMovies().filter { it.favoriteCheck }
        _favoriteMoviesLiveData.value = favoritesData as MutableList<Infos>
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addFavorite(movieId: Infos) {
        favorites.addToFavorites(movieId)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

    fun removeFavorite(movieId: Infos) {
        favorites.removeFromFavorites(movieId)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

}