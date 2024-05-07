package com.bluesky.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.adapter.YueDanAdapter
import com.bluesky.heimaplayer.base.fragment.BaseFragment
import com.bluesky.heimaplayer.model.YueDanBody
import com.bluesky.heimaplayer.presenter.impl.YueDanPresenterImpl
import com.bluesky.heimaplayer.presenter.interf.YueDanPresenter
import com.bluesky.heimaplayer.view.YueDanView

class YueDanFragment : BaseFragment(), YueDanView {
    val recyclerView: RecyclerView by lazy { root.findViewById(R.id.rv_list) }
    val swipeRefreshLayout: SwipeRefreshLayout by lazy { root.findViewById(R.id.srl_swipe) }
    val root: View by lazy {
        View.inflate(context, R.layout.fragment_yuedan, null)
    }
    val adapter: YueDanAdapter by lazy { YueDanAdapter() }
    val presenter: YueDanPresenter by lazy { YueDanPresenterImpl(this) }

    var page = 1
    override fun initView(): View {
        return root
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener {
            //下拉刷新
            page=1
            presenter.loadDatas(page, 10)
        }

        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val manager = recyclerView.layoutManager
                    if (manager is LinearLayoutManager) {
                        val position = manager.findLastCompletelyVisibleItemPosition()
                        if (position == adapter.itemCount - 1) {
                            presenter.loadDatas(++page, 10)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter.loadDatas(1, 10)
    }

    override fun onError(e: Exception) {
        myToast(e.message.toString())
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onSuccess(response: YueDanBody) {
        //这里返回的是YueDanBody,使用时只需要使用其中的video的list
        val list = response.result.list
        if (page == 1) {//第一页为下拉刷新,其他页为下拉加载更多
            adapter.updateData(list)
        } else {
            adapter.loadMoreData(list)
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun displayProgressBar() {
        swipeRefreshLayout.isRefreshing = true
    }


}