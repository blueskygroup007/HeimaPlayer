package com.bluesky.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.bluesky.heimaplayer.R

class ProgressItemView : LinearLayout{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init {
        inflate(context, R.layout.progress_item_view,this)
    }
}
