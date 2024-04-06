package com.bluesky.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.adapter.HomeAdapter
import com.bluesky.heimaplayer.base.fragment.BaseFragment
import com.bluesky.heimaplayer.model.HaoKanResult
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
    val swipeRefreshLayout: SwipeRefreshLayout by lazy { root.findViewById(R.id.srl_swipe) }
    val root: View by lazy {
        View.inflate(context, R.layout.fragment_home, null)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter() }

    companion object {
        var page: Int=1
    }

    override fun initView(): View {
        return root
    }

    override fun initListener() {
        super.initListener()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        //初始化刷新控件
        swipeRefreshLayout.setColorSchemeColors(
            Color.RED,
            Color.YELLOW,
            Color.GREEN
        )//参数为可变参数,即可为一个或多个

        swipeRefreshLayout.setOnRefreshListener {
            //刷新监听
            loadDatas(page, 10)
        }

        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //当滑动停止时,检测是否到达最后一项
                    val manager = recyclerView.layoutManager
                    if (manager is LinearLayoutManager) {
                        val position =
                            manager.findLastCompletelyVisibleItemPosition()
                        if (position == adapter.itemCount) {
                            loadDatas(page++, 10)
                        }
                    }
                }
            }
        })


    }

    /**
     * 从当前条目再读取一定数量的item
     */
    /*    private fun loadMore(page: Int, size: Int) {
            val path = URLProviderUtils.getHaoKanVideos(page, size)
            val client = OkHttpClient()
            val request = Request.Builder().get().url(path).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Timber.e("请求失败!")
                    //隐藏刷新控件
                    ThreadUtil.runOnMainThread {
                        swipeRefreshLayout.isRefreshing = false
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val result = response.body.string()
                    Timber.e(result)
                    val gson = Gson()
                    val homeItem = gson.fromJson(result, object : TypeToken<HaoKanResult>() {})
                    ThreadUtil.runOnMainThread(object : Runnable {
                        override fun run() {
                            adapter.updateList(homeItem.result.haokanVideoBean)
                            //隐藏刷新控件
                            swipeRefreshLayout.isRefreshing = false
                        }
                    })
                    Timber.e(homeItem.result.haokanVideoBean.get(0).toString())
                }

            })
        }*/

    override fun initData() {
        super.initData()
        Timber.plant(Timber.DebugTree())
        Timber.tag(this.javaClass.simpleName)
        loadDatas(1, 10)
    }

    private fun loadDatas(page: Int, size: Int) {
        val path = URLProviderUtils.getHaoKanVideos(page, size)
        val client = OkHttpClient()
        val request = Request.Builder().get().url(path).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Timber.e("请求失败!")
                //隐藏刷新控件
                ThreadUtil.runOnMainThread {
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body.string()

                Timber.e(result)
                val gson = Gson()
                /*                val homeItem =
                                    gson.fromJson<HomeItemBean>(result, object : TypeToken<HomeItemBean>() {})*/

                val homeItem = gson.fromJson(result, object : TypeToken<HaoKanResult>() {})
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.updateList(homeItem.result.haokanVideoBean)
                        //隐藏刷新控件
                        swipeRefreshLayout.isRefreshing = false
                    }
                })
                Timber.e(homeItem.result.haokanVideoBean.get(0).toString())
            }

        })
    }
}