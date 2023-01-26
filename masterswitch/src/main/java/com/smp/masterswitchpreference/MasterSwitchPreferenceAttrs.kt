package com.smp.masterswitchpreference

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Parcelable
import android.util.TypedValue
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

internal const val ATTRS_KEY_NAME = "MasterSwitchAttrs"

@Keep
@Parcelize
class MasterSwitchPreferenceAttrs(
    val switchLayout: Int = 0,
    val switchOnBackgroundColor: Int = Color.RED,
    val switchOffBackgroundColor: Int = Color.LTGRAY,
    val switchOnTextColor: Int = Color.BLACK,
    val switchOffTextColor: Int = Color.BLACK,
    val switchOffExplanationText: String = "",
    val switchOnExplanationText: String = "",
    val includedPrefScreen: Int? = null,
    val excludedPrefScreen: Int? = null,
    val switchOnText: String = "On",
    val switchOffText: String = "Off",
    val hideExplanation: Boolean = true,
    val key: String = "master_switch_key",
    val defaultValue: Boolean = false,
    val explanationIcon: Int? = null,
    val explanationIconTintColor: Int = Color.LTGRAY,
    val showStatus: Boolean = false
) : Parcelable {
    companion object {
        fun defaultFromContext(context: Context): MasterSwitchPreferenceAttrs {
            val onSurfaceVariant = resolveAttr(context, "colorOnSurfaceVariant") ?: Color.LTGRAY
            return MasterSwitchPreferenceAttrs(
                switchOnBackgroundColor = resolveAttr(context, "colorPrimaryContainer") ?: Color.RED,
                switchOffBackgroundColor = resolveAttr(context, "colorSurfaceVariant") ?: Color.LTGRAY,
                switchOnTextColor = resolveAttr(context, "colorOnPrimaryContainer") ?: Color.BLACK,
                switchOffTextColor = onSurfaceVariant,
                explanationIconTintColor = onSurfaceVariant
            )
        }
    }
}

@SuppressLint("DiscouragedApi")
private fun resolveAttr(context: Context, name: String): Int? {
    val resId = context.resources.getIdentifier(name, "attr", context.packageName)
    return if (resId == 0) null else resolveAttr(context, resId)
}

private fun resolveAttr(activity: Context, attr: Int): Int? {
    val typedValue = TypedValue()
    val valid = activity.theme.resolveAttribute(attr, typedValue, true)
    return if (valid) typedValue.data else null
}
