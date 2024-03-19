package com.bluesky.heimaplayer.ui.activity

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.bluesky.heimaplayer.R
import com.bluesky.heimaplayer.base.fragment.BaseActivity

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun getLayoutId(): Int {

        return R.layout.activity_splash
    }

    override fun initData() {
        val ivSplash = findViewById<ImageView>(R.id.iv_splash)
        ViewCompat.animate(ivSplash).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }

    override fun onAnimationStart(view: View) {
    }

    override fun onAnimationEnd(view: View) {
        /*val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)*/
        startActivityAndFinish(MainActivity::class.java)
    }

    override fun onAnimationCancel(view: View) {
    }
}