package com.bluesky.heimaplayer.model


data class YueDanVideoResult(
    var id: Int,
    var title: String,
    var alias: String,
    var picuser: String,
    var picurl: String,
    var playurl: String,
    var sec: Int
)
data class YueDanBody(var code:Int,var message:String,var result:YueDanResult)

data class YueDanResult(var total:Int,var list: List<YueDanVideoResult>)

