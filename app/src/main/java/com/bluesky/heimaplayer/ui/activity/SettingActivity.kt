package com.bluesky.heimaplayer.ui.activity

import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.base.fragment.BaseActivity
import com.bluesky.heimaplayer.util.ToolBarManager

class SettingActivity : BaseActivity(), ToolBarManager {
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        super.initData()
        initSettingToolBar()
        //获取preference设置
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push", false)
        Log.e(this.javaClass.simpleName, "$push")
    }

    override val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) }

}