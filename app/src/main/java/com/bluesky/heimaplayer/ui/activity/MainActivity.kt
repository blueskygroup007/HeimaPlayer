package com.bluesky.heimaplayer.ui.activity

import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.base.fragment.BaseActivity
import com.bluesky.heimaplayer.util.FragmentUtil
import com.bluesky.heimaplayer.util.ToolBarManager
import it.sephiroth.android.library.bottomnavigation.BottomNavigation
import it.sephiroth.android.library.bottomnavigation.BottomNavigation.OnMenuItemSelectionListener

class MainActivity : BaseActivity(), ToolBarManager, OnMenuItemSelectionListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)

    }

    override fun initData() {
        super.initData()

        initMainToolBar()
        val bottomNavigation = findViewById<BottomNavigation>(R.id.bottom_navigation)
        bottomNavigation.menuItemSelectionListener = this

        //强制选中home项以激活itemselect回调
        onMenuItemSelect(R.id.bbn_home, 0, true)
    }

    override fun onMenuItemReselect(itemId: Int, position: Int, fromUser: Boolean) {
    }

    override fun onMenuItemSelect(itemId: Int, position: Int, fromUser: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        Log.e(MainActivity::class.simpleName, "选中了${itemId}")

        when (itemId) {
            R.id.bbn_home -> {
                FragmentUtil.fragmentUtil.getFragment(itemId)
                    ?.let { transaction.replace(R.id.container, it, itemId.toString()) }
                transaction.commit()
            }

            R.id.bbn_mv -> {
                FragmentUtil.fragmentUtil.getFragment(itemId)
                    ?.let { transaction.replace(R.id.container, it, itemId.toString()) }
                transaction.commit()

            }

            R.id.bbn_mvlist -> {
                FragmentUtil.fragmentUtil.getFragment(itemId)
                    ?.let { transaction.replace(R.id.container, it, itemId.toString()) }
                transaction.commit()

            }

            R.id.bbn_vlist -> {
                FragmentUtil.fragmentUtil.getFragment(itemId)
                    ?.let { transaction.replace(R.id.container, it, itemId.toString()) }
                transaction.commit()

            }
        }
    }
}

