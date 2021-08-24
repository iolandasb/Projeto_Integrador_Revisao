package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InfosCast(
    val profile_path: String?,
    val name: String,
    val character: String,
) : Parcelable
