package com.bluesky.heimaplayer.net

import com.bluesky.heimaplayer.model.HaoKanResult
import com.bluesky.heimaplayer.util.ThreadUtil
import com.bluesky.heimaplayer.util.URLProviderUtils
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
 * @date 24.4.18
 * Description:发送网络请求类
 */
class NetManager private constructor() {

    val client by lazy { OkHttpClient() }

    companion object {
        val manager: NetManager by lazy { NetManager() }
    }

    fun <REQUEST> sendRequest(req: MRequest<REQUEST>) {
        val request = Request.Builder().get().url(req.url).build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Timber.e("请求失败!")
                //隐藏刷新控件
                ThreadUtil.runOnMainThread {
                    req.callback.onError(e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body.string()
                Timber.e(result)

                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        req.callback.onSuccess(req.parseRequest(result))
                    }
                })
            }

        })
    }
}
