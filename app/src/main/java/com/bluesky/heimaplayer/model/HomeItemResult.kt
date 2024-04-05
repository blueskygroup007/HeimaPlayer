package com.bluesky.heimaplayer.model

/**
 *
 * @author BlueSky
 * @date 24.4.5
 * Description:
 */
data class HomeItemResult(
    var title: String,
    var share_url: String,
    var author: String,
    var item_cover: String,
    var hot_value:Int,
    var hot_words:String,
    var play_count:Int,
    var digg_count:Int,
    var comment_count:Int
)