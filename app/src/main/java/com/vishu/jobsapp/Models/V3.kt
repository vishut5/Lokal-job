package com.vishu.jobsapp.Models

import com.google.gson.annotations.SerializedName

data class V3(
    @SerializedName("field_key") val fieldKey: String,
    @SerializedName("field_name") val fieldName: String,
    @SerializedName("field_value") val fieldValue: String
)