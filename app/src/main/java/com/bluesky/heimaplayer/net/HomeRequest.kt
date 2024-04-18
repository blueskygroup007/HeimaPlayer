package com.bluesky.heimaplayer.net

import com.bluesky.heimaplayer.model.HaoKanResult
import com.bluesky.heimaplayer.util.URLProviderUtils

/**
 *
 * @author BlueSky
 * @date 24.4.18
 * Description:
 */
class HomeRequest(page:Int,size:Int,callback: ResponseCallback<HaoKanResult>):MRequest<HaoKanResult>(URLProviderUtils.getHaoKanVideos(page,size),callback) {

}