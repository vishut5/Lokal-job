package com.vishu.jobsapp.di

import android.content.Context
import androidx.room.Room
import com.vishu.jobsapp.Utils.Constants.BASE_URL
import com.vishu.jobsapp.api.ApiService
import com.vishu.jobsapp.database.AppDatabase
import com.vishu.jobsapp.database.JobsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "jobs_database"
        ).build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): JobsDao {
        return database.productDao()
    }
}