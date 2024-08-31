package com.vishu.jobsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vishu.jobsapp.Models.JobsEntity

@Database(entities = [JobsEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): JobsDao
}