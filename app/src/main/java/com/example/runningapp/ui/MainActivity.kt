package com.example.runningapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningapp.R
import com.example.runningapp.databinding.ActivityMainBinding
import com.example.runningapp.utilities.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)

        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.setupWithNavController(binding.flFragment[0].findNavController())

        binding.flFragment[0].findNavController()
            .addOnDestinationChangedListener{ _, destination, _ ->
                when (destination.id){
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
            binding.flFragment[0].findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }

    fun setTextToolbarTitle(title: String) {
        binding.tvToolbarTitle.text = title
    }
}