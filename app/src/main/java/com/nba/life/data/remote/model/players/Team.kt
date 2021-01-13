package com.nba.life.data.remote.model.players


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Team(
    @SerializedName("abbreviation")
    var abbreviation: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("conference")
    var conference: String?,
    @SerializedName("division")
    var division: String?,
    @SerializedName("full_name")
    var fullName: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
) : Parcelable