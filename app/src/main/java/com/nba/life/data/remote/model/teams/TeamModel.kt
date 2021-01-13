package com.nba.life.data.remote.model.teams


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel(
    @SerializedName("data")
    var `data`: MutableList<TeamData>?,
    @SerializedName("meta")
    var meta: Meta?
) : Parcelable {
    @Parcelize
    data class TeamData(
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
}