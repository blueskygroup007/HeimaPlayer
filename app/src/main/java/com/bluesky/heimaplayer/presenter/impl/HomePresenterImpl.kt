package com.bluesky.heimaplayer.presenter.impl

import com.bluesky.heimaplayer.model.HaoKanResult
import com.bluesky.heimaplayer.presenter.interf.HomePresenter
import com.bluesky.heimaplayer.util.ThreadUtil
import com.bluesky.heimaplayer.util.URLProviderUtils
import com.bluesky.heimaplayer.view.HomeView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 *
 * @author BlueSky
 * @date 24.4.14
 * Description:
 */
class HomePresenterImpl(var/*加上var就成为成员变量*/ homeView:HomeView):HomePresenter {
    override fun loadDatas(page: Int, size: Int) {
        val path = URLProviderUtils.getHaoKanVideos(page, size)
        val client = OkHttpClient()
        val request = Request.Builder().get().url(path).build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Timber.e("请求失败!")
                //隐藏刷新控件
                ThreadUtil.runOnMainThread {
                   homeView.onLoadError(e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body.string()

                Timber.e(result)
                val gson = Gson()
                //当解析的json类型不确定,或者为泛型时,使用TypeToken
                //val homeItem = gson.fromJson(result, object : TypeToken<HaoKanResult>() {})
                val resultData = gson.fromJson(result, HaoKanResult::class.java)
                val list=resultData.result.list
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeView.onLoadSuccess(list)
                    }
                })
                Timber.e(resultData.result.list.get(0).toString())
            }

        })
    }
}