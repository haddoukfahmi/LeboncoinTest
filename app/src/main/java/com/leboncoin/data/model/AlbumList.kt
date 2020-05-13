package com.leboncoin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "album")
data class Album(
    @SerializedName("albumId") val albumId : Int,
    @PrimaryKey()
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String,
    @SerializedName("thumbnailUrl") val thumbnailUrl : String
)