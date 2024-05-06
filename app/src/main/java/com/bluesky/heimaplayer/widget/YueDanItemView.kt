package com.bluesky.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.model.YueDanVideoResult
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 *
 */
class YueDanItemView : RelativeLayout {
    fun setData(itemData: YueDanVideoResult) {
        Picasso.get().load(itemData.picuser).transform(CropCircleTransformation()).into(authorPic)
        Picasso.get().load(itemData.picurl)
            .into(backgroundPic)
        videoTitle.text = itemData.title
        videoDesc.text = itemData.alias
        videoTimeCount.text = itemData.sec.toString()
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        //这里的root参数必须为this，而不能是null
        View.inflate(context, R.layout.item_yuedan, this)

    }

    var authorPic = findViewById<ImageView>(R.id.iv_list_item_author)
    var videoTitle = findViewById<TextView>(R.id.tv_list_item_title)
    var videoTimeCount = findViewById<TextView>(R.id.tv_list_item_name)
    var videoDesc = findViewById<TextView>(R.id.tv_list_item_title_name)
    var backgroundPic = findViewById<ImageView>(R.id.iv_list_item_bg)
}