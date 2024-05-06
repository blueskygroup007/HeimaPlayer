package com.bluesky.heimaplayer.view

import com.bluesky.heimaplayer.model.YueDanBody

interface YueDanView {
    fun onError(e: Exception)
    fun onSuccess(response: YueDanBody)
    fun displayProgressBar()




}