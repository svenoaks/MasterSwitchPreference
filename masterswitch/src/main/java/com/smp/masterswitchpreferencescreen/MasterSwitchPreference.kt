package com.smp.masterswitchpreferencescreen

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import java.io.File


class MasterSwitchPreference : Preference {
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        inflateAttrs(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateAttrs(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        inflateAttrs(attrs)
    }
    constructor(context: Context?) : super(context) {}

    override fun onAttached() {
        super.onAttached()
        setupStatus()
    }

    private fun inflateAttrs(attrs: AttributeSet?) {
        val resAttrs = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.MasterSwitchPreferenceScreen,
                0,
                0
        ) ?: return

        with(resAttrs) {
            val androidAttrs = context.theme.obtainStyledAttributes(attrs, R.styleable.Preference, 0, 0)

            val includedPrefScreenRes: String? = getString(R.styleable.MasterSwitchPreferenceScreen_ms_includedPrefsScreen)
            val notIncludedPrefScreenRes: String? = getString(R.styleable.MasterSwitchPreferenceScreen_ms_excludedPrefsScreen)
            val icon: String? = getString(R.styleable.MasterSwitchPreferenceScreen_ms_explanationIcon)

            fun resInt(resName: String?, type: String): Int? {
                if (resName == null) return null
                val f = File(resName).nameWithoutExtension
                return context.resources.getIdentifier(f, type, context.packageName)
            }

            val def = MasterSwitchPreferenceAttrs()

            val attrs = MasterSwitchPreferenceAttrs(
                    switchThumbColor = getColor(R.styleable.MasterSwitchPreferenceScreen_ms_switchThumbColor, def.switchThumbColor),
                    switchTrackColor = getColor(R.styleable.MasterSwitchPreferenceScreen_ms_switchTrackColor, def.switchTrackColor),
                    switchOnBackgroundColor = getColor(R.styleable.MasterSwitchPreferenceScreen_ms_switchOnBackgroundColor, def.switchOnBackgroundColor),
                    switchOffBackgroundColor = getColor(R.styleable.MasterSwitchPreferenceScreen_ms_switchOffBackgroundColor, def.switchOffBackgroundColor),
                    switchTextColor = getColor(R.styleable.MasterSwitchPreferenceScreen_ms_switchTextColor, def.switchTextColor),
                    explanationText = getString(R.styleable.MasterSwitchPreferenceScreen_ms_explanationText)
                            ?: def.explanationText,
                    includedPrefScreenRes = resInt(includedPrefScreenRes, "xml")
                            ?: def.includedPrefScreenRes,
                    notIncludedPrefScreenRes = resInt(notIncludedPrefScreenRes, "xml")
                            ?: def.notIncludedPrefScreenRes,
                    switchTextOn = getString(R.styleable.MasterSwitchPreferenceScreen_ms_switchTextOn)
                            ?: def.switchTextOn,
                    switchTextOff = getString(R.styleable.MasterSwitchPreferenceScreen_ms_switchTextOff)
                            ?: def.switchTextOff,
                    key = this@MasterSwitchPreference.key ?: def.key,
                    defaultValue = when {
                        androidAttrs.hasValue(R.styleable.Preference_defaultValue) -> {
                            androidAttrs.getBoolean(R.styleable.Preference_defaultValue, false)
                        }
                        androidAttrs.hasValue(R.styleable.Preference_android_defaultValue) -> {
                            androidAttrs.getBoolean(R.styleable.Preference_android_defaultValue, false)
                        }
                        else -> {
                            def.defaultValue
                        }
                    },
                    hideExplanation = getBoolean(R.styleable.MasterSwitchPreferenceScreen_ms_hideExplanation, def.hideExplanation),
                    explanationIcon = resInt(icon, "drawable") ?: def.explanationIcon,
                    showStatus = getBoolean(R.styleable.MasterSwitchPreferenceScreen_ms_showStatusInSummary, def.showStatus)
            )

            val fragName = when {
                androidAttrs.hasValue(R.styleable.Preference_fragment) -> {
                    androidAttrs.getString(R.styleable.Preference_fragment)
                }
                androidAttrs.hasValue(R.styleable.Preference_android_fragment) -> {
                    androidAttrs.getString(R.styleable.Preference_android_fragment)
                }
                else -> {
                    MasterSwitchPreferenceFragment::class.qualifiedName
                }
            }
            setDefaultValue(attrs.defaultValue)
            key = attrs.key
            fragment = fragName
            extras.putParcelable("MasterSwitchAttrs", attrs)

            recycle()
            androidAttrs.recycle()
        }
    }

    private fun setupStatus() {
        val attrs = extras.getParcelable<MasterSwitchPreferenceAttrs>("MasterSwitchAttrs")!!
        if (attrs.showStatus) {
            val on = getPersistedBoolean(attrs.defaultValue)
            summary = if (on) attrs.switchTextOn else attrs.switchTextOff
        }
    }
}