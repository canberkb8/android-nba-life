package com.nba.life.data.remote.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("next_page")
    val nextPage: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
) : Parcelable