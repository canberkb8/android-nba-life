package com.nba.life.data.remote.model.players


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class PlayerModel(
    @SerializedName("data")
    var `data`: List<PlayerData>?,
    @SerializedName("meta")
    var meta: Meta?
) : Parcelable {

    @Parcelize
    data class PlayerData(
        @SerializedName("first_name")
        var firstName: String?,
        @SerializedName("height_feet")
        var heightFeet: String?,
        @SerializedName("height_inches")
        var heightInches: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("last_name")
        var lastName: String?,
        @SerializedName("position")
        var position: String?,
        @SerializedName("team")
        var team: Team?,
        @SerializedName("weight_pounds")
        var weightPounds: String?
    ) : Parcelable
}