package com.kunkun.sunnyweather

import android.app.Application
import android.content.Context

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
    }
}