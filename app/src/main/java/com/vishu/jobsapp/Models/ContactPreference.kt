package com.vishu.jobsapp.Models

import com.google.gson.annotations.SerializedName

data class ContactPreference(
    @SerializedName("preference") val preference: Int,
    @SerializedName("whatsapp_link") val whatsappLink: String,
    @SerializedName("preferred_call_start_time") val preferredCallStartTime: String,
    @SerializedName("preferred_call_end_time") val preferredCallEndTime: String
)