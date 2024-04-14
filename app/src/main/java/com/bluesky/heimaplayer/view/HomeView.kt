package com.bluesky.heimaplayer.view

import com.bluesky.heimaplayer.model.HaoKanVideoBean
import java.io.IOException

/**
 *
 * @author BlueSky
 * @date 24.4.14
 * Description:
 */
interface HomeView {
    fun onLoadError(e: IOException)
    fun onLoadSuccess(list: List<HaoKanVideoBean>)
}