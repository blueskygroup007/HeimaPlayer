package com.bluesky.heimaplayer.presenter.impl

import com.bluesky.heimaplayer.model.HaoKanResult
import com.bluesky.heimaplayer.model.YueDanBody
import com.bluesky.heimaplayer.model.YueDanVideoResult
import com.bluesky.heimaplayer.net.HomeRequest
import com.bluesky.heimaplayer.net.ResponseCallback
import com.bluesky.heimaplayer.net.YueDanRequest
import com.bluesky.heimaplayer.presenter.interf.YueDanPresenter
import com.bluesky.heimaplayer.view.YueDanView

class YueDanPresenterImpl(var yueDanView: YueDanView) : YueDanPresenter,
    ResponseCallback<YueDanBody> {
    override fun loadDatas(i: Int, i1: Int) {
        YueDanRequest(1, 10, this).excute()
        yueDanView.displayProgressBar()
    }

    override fun onError(e: Exception) {
        yueDanView.onError(e)
    }

    override fun onSuccess(response: YueDanBody) {
        yueDanView.onSuccess(response)
    }


}