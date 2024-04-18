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
import com.bluesky.heimaplayer.model.HaoKanVideoBean
import com.bluesky.heimaplayer.presenter.impl.HomePresenterImpl
import com.bluesky.heimaplayer.util.ThreadUtil
import com.bluesky.heimaplayer.util.URLProviderUtils
import com.bluesky.heimaplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import timber.log.Timber

class HomeFragment : BaseFragment(),HomeView {


    val recyclerView: RecyclerView by lazy { root.findViewById(R.id.rv_home) }
    val swipeRefreshLayout: SwipeRefreshLayout by lazy { root.findViewById(R.id.srl_swipe) }
    val root: View by lazy {
        View.inflate(context, R.layout.fragment_home, null)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter() }

    val presenter by lazy { HomePresenterImpl(this) }

    companion object {
        var page: Int = 1
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
            presenter.loadDatas(page,10)
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
                        Timber.e("position=$position")
                        if (position == adapter.itemCount - 1) {
                            presenter.loadDatas(page++, 10)
                        }
                    }
                }
            }
        })
    }


    override fun initData() {
        super.initData()
        presenter.loadDatas(1,10)
    }

    override fun onLoadError(e: Exception) {
        swipeRefreshLayout.isRefreshing = false
    }


    override fun onLoadSuccess(list: List<HaoKanVideoBean>) {
        if (page == 1) {//第一页为下拉刷新,其他页为下拉加载更多
            adapter.updateList(list)
        } else {
            adapter.loadMore(list)
        }
        //隐藏刷新控件
        swipeRefreshLayout.isRefreshing = false
    }

}