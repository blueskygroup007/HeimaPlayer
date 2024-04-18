package com.bluesky.heimaplayer.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 *
 * @author BlueSky
 * @date 24.4.18
 * Description:所有请求的基类
 */
open class MRequest<RESPONSE>(val url: String, val callback: ResponseCallback<RESPONSE>) {
    fun parseRequest(result:String):RESPONSE{
        val gson = Gson()
        //当解析的json类型不确定,或者为泛型时,使用TypeToken
        //val homeItem = gson.fromJson(result, object : TypeToken<HaoKanResult>() {})
       //return gson.fromJson(result, HaoKanResult::class.java).result.list

        //
        val type=(this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return gson.fromJson(result, type)
    }

    /**
     *发送请求也放在request内部.
     */
    fun excute(){
        NetManager.manager.sendRequest(this)
    }
}