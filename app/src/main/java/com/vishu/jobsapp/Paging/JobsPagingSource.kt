package com.vishu.jobsapp.Paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vishu.jobsapp.Models.JobDetails
import com.vishu.jobsapp.Utils.Constants.TAG
import com.vishu.jobsapp.api.ApiService

class JobsPagingSource(private val apiService: ApiService) : PagingSource<Int, JobDetails>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobDetails> {
        try {
            val position = params.key ?: 1
            val response = apiService.getAllJobs(position)
            return if (response.isSuccessful) {
                val jobs = response.body()?.results ?: emptyList()
                Log.d(TAG, "load: $jobs")
                LoadResult.Page(
                    data = jobs,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == 3) null else position + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        }
        catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobDetails>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    }

}