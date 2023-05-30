package com.kunkun.sunnyweather

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV




class SunnyWeatherApplication : Application() {
    //定义一个companion object
    companion object {
        lateinit var context: Context
        //保存token
        const val  TOKEN="sTnnWIAyGVGLh51r"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        配置 MMKV 根目录
        val rootDir = MMKV.initialize(this)
        println("mmkv root: $rootDir")
    }
}