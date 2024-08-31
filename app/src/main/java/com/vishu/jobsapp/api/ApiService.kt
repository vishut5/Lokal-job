package com.vishu.jobsapp.api

import com.vishu.jobsapp.Models.JobResponse
import com.vishu.jobsapp.Utils.Constants.ALL_JOBS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_JOBS)
    suspend fun getAllJobs(@Query("page") page: Int): Response<JobResponse>

}