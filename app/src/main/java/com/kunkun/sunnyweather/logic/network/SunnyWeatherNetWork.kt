package com.kunkun.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//对所有的网络请求进行封装
object SunnyWeatherNetWork {
    //先获取serviceapi
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    //挂机函数调用接口
    suspend fun searchPlace(query: String) = placeService.searchPlace(query).await()


    //获取weatherservice
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun getRealTimeWeather(lng: Double, lat: Double) =weatherService.getRealTimeWeather(lng, lat).await()


    suspend fun getDailyWeather(lng: Double, lat: Double) = weatherService.getDailyWeather(lng, lat).await()

    // 使用协程对retroft的请求进行简化
    private suspend fun <T> Call<T>.await(): T {
        // 协程简化
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException(RuntimeException("body id empty "))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }

            })
        }
    }
}