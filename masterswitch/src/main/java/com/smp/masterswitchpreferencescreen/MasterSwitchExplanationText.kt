package com.smp.masterswitchpreferencescreen

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder

internal class MasterSwitchExplanationText : Preference {
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.itemView?.let { view ->
            val frame = view.findViewById<View>(R.id.icon_frame)
            frame.updatePadding(top = dpToPixels(view.context, 18.0f).toInt())
            val params = frame.layoutParams as LinearLayout.LayoutParams
            params.gravity = Gravity.TOP
            frame.layoutParams = params
        }
    }
}