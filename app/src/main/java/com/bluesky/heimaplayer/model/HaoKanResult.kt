package com.bluesky.heimaplayer.model

/**
 *
 * @author BlueSky
 * @date 24.4.6
 * Description:
 */
data class HaoKanResult (var code:Int, var message:String, var result :HaoKanBean)

data class HaoKanBean (var total:Int,var list:List<HaoKanVideoBean>)

data class HaoKanVideoBean(
    var id: Int,
    var title: String,
    var userName: String,
    var userPic: String,
    var coverUrl: String,
    var playUrl: String,
    var duration: String
)
