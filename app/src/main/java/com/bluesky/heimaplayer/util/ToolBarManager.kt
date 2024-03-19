package com.bluesky.heimaplayer.util

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.ui.activity.MainActivity
import com.bluesky.heimaplayer.ui.activity.SettingActivity

/**
 * 所有界面的toolbar的管理类
 * 定义为interface，便于继承，且方法允许有实体
 */
interface ToolBarManager {
    val toolbar: Toolbar
    fun initMainToolBar() {

        toolbar.title = "黑马影音xxx"
        toolbar.inflateMenu(R.menu.menu_main)

        //前面是toolbar的监听，后面必须保持一致，不能是menu的监听
        toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.setting -> {
                    Toast.makeText(toolbar.context, "点击了设置按钮", Toast.LENGTH_SHORT).show()
                    Log.e(MainActivity::class.simpleName, "选中了${item.title}")
                    toolbar.context.startActivity(
                        Intent(
                            toolbar.context,
                            SettingActivity::class.java
                        )
                    )
                }
            }
            true
        }
    }

    fun initSettingToolBar(){
        toolbar.title="设置界面"
    }
}


