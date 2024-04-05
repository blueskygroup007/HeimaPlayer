package com.bluesky.heimaplayer.ui.fragment

import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.adapter.HomeAdapter
import com.bluesky.heimaplayer.base.fragment.BaseFragment
import com.bluesky.heimaplayer.model.HomeItemBean
import com.bluesky.heimaplayer.util.ThreadUtil
import com.bluesky.heimaplayer.util.URLProviderUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import timber.log.Timber

class HomeFragment : BaseFragment() {
    val recyclerView: RecyclerView by lazy { root.findViewById(R.id.rv_home) }
    val root: View by lazy {
        View.inflate(context, R.layout.fragment_home, null)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter() }

    override fun initView(): View {
        return root
    }

    override fun initListener() {
        super.initListener()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun initData() {
        super.initData()
        Timber.plant(Timber.DebugTree())
        Timber.tag(this.javaClass.simpleName)
        loadDatas()
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHotVideos(12)
        val client = OkHttpClient()
        val request = Request.Builder().get().url(path).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Timber.e("请求失败!")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body.string()
                Timber.e(result)
                val gson = Gson()
                val homeItem =
                    gson.fromJson<HomeItemBean>(result, object : TypeToken<HomeItemBean>() {})
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.updateList(homeItem.result)
                    }
                })
                Timber.e(homeItem.result.get(0).toString())
            }

        })
    }
}