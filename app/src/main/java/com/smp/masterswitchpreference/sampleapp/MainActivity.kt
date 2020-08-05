package com.smp.masterswitchpreference.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smp.masterswitchpreference.MasterSwitchPreferenceAttrs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun fragmentClick(view: View) {
        startActivity(Intent(this, FragmentActivity::class.java))
    }
    fun settingsClick(view: View) {
       startActivity(Intent(this, SettingsActivity::class.java))
    }
    fun navigationClick(view: View) {
        startActivity(Intent(this, NavigationActivity::class.java))
    }
}