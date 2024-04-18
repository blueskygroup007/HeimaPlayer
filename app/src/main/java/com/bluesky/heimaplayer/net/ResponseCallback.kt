package com.bluesky.heimaplayer.net

/**
 *
 * @author BlueSky
 * @date 24.4.18
 * Description:
 */
interface ResponseCallback<RESPONSE> {
    fun onError(e:Exception)
    fun onSuccess(response:RESPONSE)
}