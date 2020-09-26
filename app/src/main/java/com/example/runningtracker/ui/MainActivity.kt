package com.example.runningtracker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.runningtracker.R
import com.example.runningtracker.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.runningtracker.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!this::navController.isInitialized) {
            navController = this.findNavController(R.id.nav_host_fragment)
        }
        //Calls if activity destroyed
        navigateToTrackingFragmentIfNeeded(intent)
      //  NavigationUI.setupActionBarWithNavController(this, navController)
        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navController)
        // when click to open already opened fragment it doesn't reopen
        bottomNavigationView.setOnNavigationItemReselectedListener { /* Nothing do */ }

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.settingsFragment, R.id.statisticsFragment, R.id.runFragment -> bottomNavigationView.visibility = View.VISIBLE
                else -> bottomNavigationView.visibility = View.GONE
            }
        }
        //NavigationUI.setupWithNavController(bottomNavigationView,  navController)
    }

    //Calls if activity isn't destroyed
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            navController.navigate(R.id.action_globaltracking_fragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp()
    }
}