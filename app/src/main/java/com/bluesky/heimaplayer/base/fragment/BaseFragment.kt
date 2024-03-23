package com.bluesky.heimaplayer.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //返回自定义的view，注释了父类的方法。父类中没有view构建吗？initView不应该放在onViewCreated中吗？
        return initView()
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    protected open fun init() {
    }
    protected open fun initData() {
    }

    protected open fun initListener() {
    }

    abstract fun initView():View

    fun myToast(msg: String) {
        activity?.runOnUiThread { Toast.makeText(activity, msg, Toast.LENGTH_SHORT) }
    }

    fun log(msg: String) {
        activity?.runOnUiThread { Log.e(this.tag, msg) }
    }
}