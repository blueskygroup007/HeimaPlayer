package com.bluesky.heimaplayer.presenter.impl

import com.bluesky.heimaplayer.model.HaoKanResult
import com.bluesky.heimaplayer.net.HomeRequest
import com.bluesky.heimaplayer.net.ResponseCallback
import com.bluesky.heimaplayer.presenter.interf.HomePresenter
import com.bluesky.heimaplayer.view.HomeView

/**
 *
 * @author BlueSky
 * @date 24.4.14
 * Description:
 */
class HomePresenterImpl(var/*加上var就成为成员变量*/ homeView : HomeView?) : HomePresenter,
    ResponseCallback<HaoKanResult> {

    /**
     * 解绑P层和View层
     */
    fun destoryView(){
        if (homeView!=null){
            homeView=null
        }
    }
    override fun loadDatas(page: Int, size: Int) {

        //1.生成MRequest
        HomeRequest(page, size, this).excute()
        //2.使用NetManager发送MRequest
        //NetManager.manager.sendRequest(request)
    }

    override fun onError(e: Exception) {
        homeView?.onLoadError(e)
    }

    override fun onSuccess(response: HaoKanResult) {
        homeView?.onLoadSuccess(response.result.list)
    }

}