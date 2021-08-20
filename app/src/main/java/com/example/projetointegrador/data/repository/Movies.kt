package com.example.projetointegrador.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.projetointegrador.data.model.Infos

class Movies {

   @RequiresApi(Build.VERSION_CODES.N)

   fun setList(movies: MutableList<Infos>) {
       moviesList.addAll(movies)
   }

    fun addToFavorites(movie: Infos) {
        val position = moviesList.indexOf(movie)
        if (position == -1) {
            moviesList.add(movie)
            listFavoritesMovies()
        } else {
            moviesList[position] = moviesList[position].copy(favoriteCheck = true)
        }
    }

    fun removeFromFavorites(movie: Infos) {
        val index = moviesList.indexOf(movie)
        moviesList[index] = moviesList[index].copy(favoriteCheck = false)
    }

    fun listFavoritesMovies(): MutableList<Infos> {
        return moviesList
    }

    private companion object {
        val moviesList: MutableList<Infos> = mutableListOf()
    }


}