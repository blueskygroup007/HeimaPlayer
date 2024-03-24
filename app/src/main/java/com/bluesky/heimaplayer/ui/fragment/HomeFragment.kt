package com.bluesky.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.adapter.HomeAdapter
import com.bluesky.heimaplayer.base.fragment.BaseFragment

class HomeFragment : BaseFragment() {
    val recyclerView: RecyclerView by lazy { root.findViewById(R.id.rv_home) }
    val root: View by lazy {
        View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initView(): View {
        return root
    }

    override fun initListener() {
        super.initListener()
        val adapter = HomeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}