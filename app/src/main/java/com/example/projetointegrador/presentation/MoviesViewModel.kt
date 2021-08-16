package com.example.projetointegrador.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetointegrador.data.model.*
import com.example.projetointegrador.data.repository.Favorites
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(private val error: ErrorListener? = null) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())
    val moviesLiveData: LiveData<MutableList<Infos>> = _moviesLiveData

    private val _moviesDetailsLiveData = MutableLiveData<ReleaseDatesResponse>()
    val detailsLiveData: LiveData<ReleaseDatesResponse> = _moviesDetailsLiveData

    private val _runtimeLiveData = MutableLiveData<Runtime>()
    val runtimeLiveData: LiveData<Runtime> = _runtimeLiveData

    private val _castLiveData = MutableLiveData<List<InfosCast>>()
    val castLiveData: LiveData<List<InfosCast>> = _castLiveData

    private val _allGenresLiveData = MutableLiveData<List<AllMoviesGenres>>()
    val allGenresLiveData: LiveData<List<AllMoviesGenres>> = _allGenresLiveData

    private val _searchLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())
    val searchLiveData: LiveData<MutableList<Infos>> = _searchLiveData

    val _favoriteMoviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf())

    var favorites = Favorites()

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
    fun getInfosDetails(movieId : Int) {
        val fetchDetailsUseCase = FetchDetailsUseCase(movieId = movieId)
        fetchDetailsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _moviesDetailsLiveData.value = it
            },{
            error?.pageError()
            })
    }

    @SuppressLint("CheckResult")
    fun getMoviesRuntime(movieId5 : Int) {
        val fetchRuntimeUseCase = FetchRuntimeUseCase(movieId5 = movieId5)
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
    fun getGenresInfos(movieId3: Int) {
        val fetchGenresCastUseCase = FetchGenresUseCase(movieId3 = movieId3)
        fetchGenresCastUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _allGenresLiveData.value = it.genres
            },{
            error?.pageError()
            })
    }

    fun getGenresFavorites(genre_ids: List<Int>) {
        val favoritesMovies = favorites.listFavoritesMovies().filter { it.favoriteCheck }
            .filter { infos -> infos.genre_ids.containsAll(genre_ids) }
            .toMutableList()
            _favoriteMoviesLiveData.value = favoritesMovies
    }

    @SuppressLint("CheckResult")
    fun getGenresSelect(movieId4 : List<Int>) {
        val fetchSelectGenresUseCase = FetchSelectGenresUseCase(movieId4 = movieId4)
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
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _searchLiveData.value = it.results
            },{
            error?.pageError()
            })
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