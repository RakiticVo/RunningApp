package com.example.runningapp.repositories

import com.example.runningapp.dao.RunDAO
import com.example.runningapp.models.Run
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()

    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()

    fun getAverageSpeed() = runDAO.getAverageSpeed()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
}