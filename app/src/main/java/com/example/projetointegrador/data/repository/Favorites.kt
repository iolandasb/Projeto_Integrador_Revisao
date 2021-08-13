package com.example.projetointegrador.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.projetointegrador.data.model.Infos

class Favorites {

   @RequiresApi(Build.VERSION_CODES.N)

   fun setList(movies: MutableList<Infos>) {
       favoritesList.addAll(movies)
   }

    fun addToFavorites(movie: Infos) {
        val position = favoritesList.indexOf(movie)
        favoritesList[position] = favoritesList[position].copy(favoriteCheck = true)
    }

    fun removeFromFavorites(movie: Infos) {
        val index = favoritesList.indexOf(movie)
        favoritesList[index] = favoritesList[index].copy(favoriteCheck = false)
    }

    fun listFavoritesMovies(): MutableList<Infos> {
        return favoritesList
    }

    private companion object {
        val favoritesList: MutableList<Infos> = mutableListOf()
    }


}