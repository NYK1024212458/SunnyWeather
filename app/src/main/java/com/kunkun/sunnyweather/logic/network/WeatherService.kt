package com.kunkun.sunnyweather.logic.network

import com.kunkun.sunnyweather.SunnyWeatherApplication
import com.kunkun.sunnyweather.logic.model.DailyResponse
import com.kunkun.sunnyweather.logic.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    // 获取天气的jiek   https://api.caiyunapp.com/v2.6/sTnnWIAyGVGLh51r/101.6656,39.2072/realtime
    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime")
    fun getRealTimeWeather(@Path("lng") lng:Double,@Path("lat")lat:Double):Call<RealTimeResponse>


    //获取某一天的天气:   未来15天逐天预报: https://api.caiyunapp.com/v2.6/sTnnWIAyGVGLh51r/101.6656,39.2072/daily?dailysteps=1
    //   dailysteps=15 表示获取的是十五天的数据
    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily?dailysteps=15")
    fun getDailyWeather(@Path("lng")lng: Double,@Path("lat") lat: Double):Call<DailyResponse>
}