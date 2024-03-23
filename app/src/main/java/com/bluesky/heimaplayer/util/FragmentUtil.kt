package com.bluesky.heimaplayer.util

import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.base.fragment.BaseFragment
import com.bluesky.heimaplayer.ui.fragment.HomeFragment
import com.bluesky.heimaplayer.ui.fragment.MvFragment
import com.bluesky.heimaplayer.ui.fragment.VBangFragment
import com.bluesky.heimaplayer.ui.fragment.YueDanFragment

class FragmentUtil private constructor() {
    //该类的主构造方法是私有的，表示它是一个单例类。下方的四个成员变量不必非要放在init块中。
    private val homeFragment by lazy { HomeFragment() }
    private val mvFragment by lazy { MvFragment() }
    private val vbangFragment by lazy { VBangFragment() }
    private val yuedanFragment by lazy { YueDanFragment() }

    //私有化构造方法(单例)
    companion object {
        val fragmentUtil: FragmentUtil by lazy { FragmentUtil() }
    }

    fun getFragment(tabId: Int): BaseFragment? {
        when (tabId) {
            R.id.bbn_home -> return homeFragment
            R.id.bbn_mv -> return mvFragment
            R.id.bbn_vlist -> return vbangFragment
            R.id.bbn_mvlist -> return yuedanFragment
        }
        return null
    }
}