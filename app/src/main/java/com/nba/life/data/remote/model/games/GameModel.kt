package com.nba.life.data.remote.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameModel(
    @SerializedName("data")
    val `data`: MutableList<GameData>?,
    @SerializedName("meta")
    val meta: Meta?
) : Parcelable {
    @Parcelize
    data class GameData(
        @SerializedName("date")
        val date: String?,
        @SerializedName("home_team")
        val homeTeam: HomeTeam?,
        @SerializedName("home_team_score")
        val homeTeamScore: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("period")
        val period: Int?,
        @SerializedName("postseason")
        val postseason: Boolean?,
        @SerializedName("season")
        val season: Int?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("time")
        val time: String?,
        @SerializedName("visitor_team")
        val visitorTeam: VisitorTeam?,
        @SerializedName("visitor_team_score")
        val visitorTeamScore: Int?
    ) : Parcelable
}

