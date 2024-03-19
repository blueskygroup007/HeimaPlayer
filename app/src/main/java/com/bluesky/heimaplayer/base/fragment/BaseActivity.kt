package com.bluesky.heimaplayer.base.fragment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()

    }

    protected open fun initData() {
    }

    protected open fun initListener() {
    }

    abstract fun getLayoutId(): Int

    /**
     * 无法成功弹出toast，应该是context不对
     */
    fun myToast(msg: String) {
        runOnUiThread { Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show() }
    }

    fun <T : BaseActivity> startActivityAndFinish(activity: Class<T>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }
}