package com.example.runningtracker.ui.run

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runningtracker.R
import com.example.runningtracker.enums.SortTypes
import com.example.runningtracker.util.Constants
import com.example.runningtracker.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_run.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run), EasyPermissions.PermissionCallbacks{
    private val viewModel: RunViewModel by viewModels()

    private lateinit var runAdapter: RunAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        initRecyclerView()
        initSpinner()
        observeData()
        initClickListeners()
    }
    
    private fun initSpinner() {
        when(viewModel.sortType) {
            SortTypes.DATE -> spFilter.setSelection(0)
            SortTypes.RUNNING_TIME -> spFilter.setSelection(1)
            SortTypes.DISTANCE -> spFilter.setSelection(2)
            SortTypes.AVG_SPEED -> spFilter.setSelection(3)
            SortTypes.CALORIES_BURNED -> spFilter.setSelection(4)
        }
    }
    
    private fun initClickListeners() {
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }
        
        spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> viewModel.sortRuns(SortTypes.DATE)
                    1 -> viewModel.sortRuns(SortTypes.RUNNING_TIME)
                    2 -> viewModel.sortRuns(SortTypes.DISTANCE)
                    3 -> viewModel.sortRuns(SortTypes.AVG_SPEED)
                    4 -> viewModel.sortRuns(SortTypes.CALORIES_BURNED)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun observeData() {
        viewModel.runs.observe(viewLifecycleOwner) {
            runAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() = rvRuns.apply {
        runAdapter = RunAdapter()
        adapter = runAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
    private fun requestPermissions() {

        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions!!!",
                Constants.LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions!!!",
                Constants.LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}