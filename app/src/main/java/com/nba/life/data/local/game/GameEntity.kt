package com.nba.life.data.local.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(

    @PrimaryKey val uid: Int? = null,

    @ColumnInfo(name = "game_id")
    var gameId: Int?,

    @ColumnInfo(name = "date")
    var gameDate: String?,

    @ColumnInfo(name = "home_team")
    var homeTeam: String?,

    @ColumnInfo(name = "home_team_score")
    var homeTeamScore: Int?,

    @ColumnInfo(name = "period")
    var period: Int?,

    @ColumnInfo(name = "post_season")
    var isPostSeason: Boolean?,

    @ColumnInfo(name = "season")
    var season: Int?,

    @ColumnInfo(name = "status")
    var status: String?,

    @ColumnInfo(name = "time")
    var time: String?,

    @ColumnInfo(name = "visitor_team")
    var visitorTeam: String?,

    @ColumnInfo(name = "visitor_team_score")
    var visitorTeamScore: Int?,
)