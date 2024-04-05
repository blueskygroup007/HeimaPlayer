package com.bluesky.heimaplayer.util

import android.os.Handler
import android.os.Looper

/**
 *
 * @author BlueSky
 * @date 24.4.5
 * Description:
 *
 * 用途:在子线程中给主线程消息队列增加一个runnable
 * 使用object关键字生成了一个单例类.
 */
object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())
    fun runOnMainThread(runnable: Runnable) {
        handler.post(runnable)
    }
}