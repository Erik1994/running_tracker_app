package com.example.runningtracker.ui.tracking

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runningtracker.R
import com.example.runningtracker.ui.run.RunViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment: Fragment(R.layout.fragment_statistics) {
    private val viewModel: TrackingViewModel by viewModels()
}