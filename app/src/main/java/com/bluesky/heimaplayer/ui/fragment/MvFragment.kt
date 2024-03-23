package com.bluesky.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.bluesky.heimaplayer.base.fragment.BaseFragment

class MvFragment:BaseFragment() {
    override fun initView(): View {
        val tv= TextView(context)
        tv.gravity= Gravity.CENTER
        tv.setTextColor(Color.RED)
        //当前class的类名
        tv.text=javaClass.simpleName
        return tv
    }
}