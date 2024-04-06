package com.bluesky.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.model.HaoKanVideoBean
import com.squareup.picasso.Picasso

/**
 *
 * @author BlueSky
 * @date 24.3.24
 * Description:
 */
class HomeItemView : RelativeLayout {
    fun setData(data: HaoKanVideoBean) {
        //名称
        findViewById<TextView>(R.id.tv_home_item_title).text = data.title
        findViewById<TextView>(R.id.tv_home_item_desc).text = data.userName
        Picasso.get().load(data.coverUrl).into(findViewById<ImageView>(R.id.iv_home_item_bg))
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        View.inflate(context, R.layout.item_home, this)
    }
}