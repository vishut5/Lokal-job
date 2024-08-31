package com.vishu.jobsapp.Models

import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("results") val results: List<JobDetails>
)