package com.bluesky.heimaplayer.ui.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.base.fragment.BaseActivity
import com.bluesky.heimaplayer.util.ToolBarManager
import it.sephiroth.android.library.bottomnavigation.BottomNavigation
import it.sephiroth.android.library.bottomnavigation.BottomNavigation.OnMenuItemSelectionListener

class MainActivity : BaseActivity(), ToolBarManager {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)

    }

    override fun initData() {
        super.initData()

        //toolbar.title = "黑马影音xxx"
        //toolbar.inflateMenu(R.menu.menu_main)
        //setSupportActionBar(toolbar)
        initMainToolBar()

        val bottomNavigation = findViewById<BottomNavigation>(R.id.bottom_navigation)
        bottomNavigation.menuItemSelectionListener = (object : OnMenuItemSelectionListener {
            override fun onMenuItemReselect(itemId: Int, position: Int, fromUser: Boolean) {
            }

            @SuppressLint("LogNotTimber")
            override fun onMenuItemSelect(itemId: Int, position: Int, fromUser: Boolean) {
                when (itemId) {
                    R.id.bbn_home -> {
                        myToast("选中了${itemId}")
                        Log.e(MainActivity::class.simpleName, "选中了${itemId}")

                    }

                    R.id.bbn_mv -> {
                        myToast("选中了${itemId}")
                        Log.e(MainActivity::class.simpleName, "选中了${itemId}")

                    }

                    R.id.bbn_mvlist -> {
                        myToast("选中了${itemId}")
                        Log.e(MainActivity::class.simpleName, "选中了${itemId}")

                    }

                    R.id.bbn_vlist -> {
                        myToast("选中了${itemId}")
                        Log.e(MainActivity::class.simpleName, "选中了${itemId}")

                    }
                }
            }

        })
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }*/
}

