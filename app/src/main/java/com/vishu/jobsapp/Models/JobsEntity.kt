package com.vishu.jobsapp.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val companyName: String,
    val salary: String,
    val place: String,
    val jobType: String,
    val experience: String,
    val qualification: String,
    val jobRole : String
)