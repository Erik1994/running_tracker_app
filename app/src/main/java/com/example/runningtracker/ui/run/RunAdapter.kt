package com.example.runningtracker.ui.run

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningtracker.R
import com.example.runningtracker.db.Run
import com.example.runningtracker.util.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter: RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(run: Run) {
            itemView.apply {
                Glide.with(itemView.context).load(run.img).into(ivRunImage)
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = run.runTimeStamp
                }

                val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
                tvDate.text = dateFormat.format(calendar.time)

                val avgSpeed = "${run.averageSpeed}km/h"
                tvAvgSpeed.text = avgSpeed

                val distanceInKm = "${run.distanceMeters / 1000f}km"
                tvDistance.text = distanceInKm

                tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.runDurationInMillis)

                val caloriesBurned = "${run.caloriesBurned}kcal"
                tvCalories.text = caloriesBurned
            }
        }
    }

    val diffUtilCallback = object: DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
    // do actions asynchronously in the background
    val differ = AsyncListDiffer(this, diffUtilCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_run,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.bind(run)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}