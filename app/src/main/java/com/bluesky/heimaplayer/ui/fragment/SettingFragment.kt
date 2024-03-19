package com.bluesky.heimaplayer.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.ui.activity.AboutActivity

class SettingFragment : PreferenceFragment() {
    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(
        preferenceScreen: PreferenceScreen?,
        preference: Preference?
    ): Boolean {
        when (preference?.key) {
            "clear_cache" -> {
                Log.e(this::class.simpleName, "${preference.key}")
            }

            "about" -> {
                context.startActivity(Intent(context, AboutActivity::class.java))
            }
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}