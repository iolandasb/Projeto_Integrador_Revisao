package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReleaseDatesResponse(
    val id: Int,
    val results: List<GuidanceResponse>,
) : Parcelable {

    @Parcelize
    data class GuidanceResponse(
        val iso_3166_1: String,
        val release_dates: List<ReleaseDate>,
    ) : Parcelable

    @Parcelize
    data class ReleaseDate(
        val certification: String,
        val type: Int,
    ) : Parcelable

    @Parcelize
    data class ReleaseInfo(val certification: ReleaseInfo) : Parcelable

    override fun toString(): String {
        var certIso = ""
        for (i in results) {
            if (i.iso_3166_1 == "BR") {
                certIso += i.release_dates[0].certification
            }
        }
        return certIso
    }

}
