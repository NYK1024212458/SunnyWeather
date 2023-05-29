package com.kunkun.sunnyweather.logic.network

import com.kunkun.sunnyweather.SunnyWeatherApplication
import com.kunkun.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 关键字获取place的接口
// https://api.caiyunapp.com/v2/place?query=%E8%A5%BF%E5%AE%89&token=sTnnWIAyGVGLh51r%20&lang=zh_CN
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlace(@Query("query")query: String):Call<PlaceResponse>
}