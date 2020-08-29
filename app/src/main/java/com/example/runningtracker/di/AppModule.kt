package com.example.runningtracker.di

import android.content.Context
import androidx.room.Room
import com.example.runningtracker.db.RunningDb
import com.example.runningtracker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRunningDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            RunningDb::class.java,
            Constants.RUNNING_DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDb) = db.getRunDao()
}