package com.smp.masterswitchpreference.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.smp.masterswitchpreference.MasterSwitchPreferenceAttrs
import com.smp.masterswitchpreference.MasterSwitchPreferenceFragment

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        title = getString(R.string.title_master_switch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val frag = MasterSwitchPreferenceFragment.newInstance(
            MasterSwitchPreferenceAttrs(
                switchOnText = "Enabled",
                switchOffText = "Disabled",
                switchOffBackgroundColor = ContextCompat.getColor(this, R.color.md_grey_600),
                switchOnBackgroundColor = ContextCompat.getColor(this, R.color.md_red_900),
                switchOnTextColor = ContextCompat.getColor(this, android.R.color.primary_text_dark),
                switchOffExplanationText = getString(R.string.off_explanation),
                explanationIcon = R.drawable.ic_baseline_cake_24,
                includedPrefScreen = R.xml.settings_master_switch_1
            )
        )

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, frag)
            .commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}