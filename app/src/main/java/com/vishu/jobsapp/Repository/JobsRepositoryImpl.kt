package com.vishu.jobsapp.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.vishu.jobsapp.Models.JobsEntity
import com.vishu.jobsapp.Paging.JobsPagingSource
import com.vishu.jobsapp.api.ApiService
import com.vishu.jobsapp.database.JobsDao
import javax.inject.Inject

class JobsRepositoryImpl @Inject constructor(private val apiService: ApiService, private val jobsDao: JobsDao) {
    fun getAllJobs() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = { JobsPagingSource(apiService) }
    ).liveData

    suspend fun savedJobsLocally(savedJobs: JobsEntity) {
        jobsDao.insertProducts(listOf(savedJobs))

    }

    suspend fun getLocalProducts(): List<JobsEntity> {
        return jobsDao.getAllProducts()
    }
}