package com.example.runningapp.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.example.runningapp.databinding.FragmentStatisticsBinding
import com.example.runningapp.databinding.MarkerViewBinding
import com.example.runningapp.models.Run
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ViewConstructor")
class CustomMarkerView(
    private val runs: List<Run>,
    context: Context,
    layoutID: Int
): MarkerView(context, layoutID){
    private val binding = MarkerViewBinding.inflate(LayoutInflater.from(context), this, true)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null){
            return
        }
        val currentRunID = e.x.toInt()
        val run = runs[currentRunID]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(calendar.time)

        val avgSpeed = "${run.avgSpeedInKMH}km/h"
        binding.tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        binding.tvDistance.text = distanceInKm

        binding.tvDuration.text = Tracking.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}kcal"
        binding.tvCaloriesBurned.text = caloriesBurned
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }
}