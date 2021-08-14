package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Runtime (val id: Int,
                    val runtime : Int): Parcelable
