package com.example.runningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.runningapp.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    mainRepository: MainRepository
): ViewModel() {

    val totalTimeRun = mainRepository.getTotalTimeInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val averageSpeed = mainRepository.getAverageSpeed()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()

}