package com.vishu.jobsapp.Models

import com.google.gson.annotations.SerializedName

data class Creative(
    @SerializedName("file") val file: String,
    @SerializedName("thumb_url") val thumbUrl: String,
    @SerializedName("creative_type") val creativeType: Int
)