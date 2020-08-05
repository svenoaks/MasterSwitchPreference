package com.smp.masterswitchpreference.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class NavigationActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference
    ): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        val navDestination = navController.graph.find { target ->
            val keys = target.arguments.keys
            keys.find<String> { it == pref.fragment } != null
        }

        pref.extras.putString("title", pref.title.toString())

        navDestination?.let { target ->
            navController.navigate(target.id, pref.extras)
        }
        return true
    }
}