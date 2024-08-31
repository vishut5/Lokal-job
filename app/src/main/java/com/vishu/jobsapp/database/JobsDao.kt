package com.vishu.jobsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vishu.jobsapp.Models.JobsEntity

@Dao
interface JobsDao {

    @Insert
    fun insertProducts(products: List<JobsEntity>)

    @Query("SELECT * FROM jobs")
    fun getAllProducts(): List<JobsEntity>
}