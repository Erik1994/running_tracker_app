package com.example.runningtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.runningtracker.R
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
      //  NavigationUI.setupActionBarWithNavController(this, navController)
        setSupportActionBar(toolbar)
       // bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.settingsFragment, R.id.statisticsFragment, R.id.runFragment -> bottomNavigationView.visibility = View.VISIBLE
                else -> bottomNavigationView.visibility = View.GONE
            }
        }
        NavigationUI.setupWithNavController(bottomNavigationView,  navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp()
    }
}