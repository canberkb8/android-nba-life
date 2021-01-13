package com.nba.life.data.remote.model.players


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Meta(
    @SerializedName("current_page")
    var currentPage: Int?,
    @SerializedName("next_page")
    var nextPage: Int?,
    @SerializedName("per_page")
    var perPage: Int?,
    @SerializedName("total_count")
    var totalCount: Int?,
    @SerializedName("total_pages")
    var totalPages: Int?
) : Parcelable