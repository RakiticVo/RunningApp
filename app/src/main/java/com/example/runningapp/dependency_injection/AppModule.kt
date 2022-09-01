package com.example.runningapp.dependency_injection

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.runningapp.dao.RunningDB
import com.example.runningapp.utilities.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.runningapp.utilities.Constants.KEY_NAME
import com.example.runningapp.utilities.Constants.KEY_WEIGHT
import com.example.runningapp.utilities.Constants.RUNNING_DATABASE_NAME
import com.example.runningapp.utilities.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDB::class.java,
        RUNNING_DATABASE_NAME,
    ).build()

    @Singleton
    @Provides
    fun provideRunDAO(db: RunningDB) = db.getRunDAO()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)!!

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) = sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) = sharedPreferences.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) =
        sharedPreferences.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}