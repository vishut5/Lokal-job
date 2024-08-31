package com.vishu.jobsapp.Models

import com.google.gson.annotations.SerializedName

data class JobTag(
    @SerializedName("value") val value: String,
    @SerializedName("bg_color") val bgColor: String,
    @SerializedName("text_color") val textColor: String
)