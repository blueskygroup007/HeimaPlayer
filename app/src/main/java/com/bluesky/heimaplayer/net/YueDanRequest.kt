package com.bluesky.heimaplayer.net

import com.bluesky.heimaplayer.model.YueDanBody
import com.bluesky.heimaplayer.model.YueDanVideoResult
import com.bluesky.heimaplayer.util.URLProviderUtils

class YueDanRequest(page: Int, size: Int, callback: ResponseCallback<YueDanBody>) :
    MRequest<YueDanBody>(URLProviderUtils.getMiniVideos(page, size), callback) {
}