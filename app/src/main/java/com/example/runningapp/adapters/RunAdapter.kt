package com.example.runningapp.adapters

import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningapp.databinding.ItemRunBinding
import com.example.runningapp.models.Run
import com.example.runningapp.utilities.Tracking
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter: RecyclerView.Adapter<RunAdapter.RunViewHolder>() {
    inner class RunViewHolder(val binding : ItemRunBinding) : RecyclerView.ViewHolder(binding.root)

    private var diffCallback = object: DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private var differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RunViewHolder {
        val binding = ItemRunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RunViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        val bindingView = holder.binding
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(bindingView.ivRunImage)

            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            bindingView.tvDate.text = dateFormat.format(calendar.time)

            val avgSpeed = "${run.avgSpeedInKMH}km/h"
            bindingView.tvAvgSpeed.text = avgSpeed

            val distanceInKm = "${run.distanceInMeters / 1000f}km"
            bindingView.tvDistance.text = distanceInKm

            bindingView.tvTime.text = Tracking.getFormattedStopWatchTime(run.timeInMillis)

            val caloriesBurned = "${run.caloriesBurned}kcal"
            bindingView.tvCalories.text = caloriesBurned
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}