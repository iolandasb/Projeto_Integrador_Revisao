package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Infos (val id: Int,
                  val vote_average : Number,
                  val title : String,
                  val poster_path : String,
                  val backdrop_path : String,
                  val overview : String,
                  val release_date : String,
                  val genre_ids : List<Int>,
                  val runtime : Int?,
                  var favoriteCheck : Boolean = false): Parcelable