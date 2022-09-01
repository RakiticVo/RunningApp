package com.example.runningapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.runningapp.models.Run
import com.example.runningapp.utilities.Converter

@Database(
    entities = [Run::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class RunningDB: RoomDatabase() {

    abstract fun getRunDAO(): RunDAO
}