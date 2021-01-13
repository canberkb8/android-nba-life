package com.nba.life.data.remote.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeTeam(
    @SerializedName("abbreviation")
    val abbreviation: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("conference")
    val conference: String?,
    @SerializedName("division")
    val division: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
) : Parcelable