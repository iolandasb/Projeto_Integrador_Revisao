package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllMoviesGenres (val id : Int,
                            val name : String) : Parcelable
