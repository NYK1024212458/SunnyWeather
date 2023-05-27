package com.kunkun.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    //  添加baseurl
    private const val BASE_URL="https://api.caiyunapp.com/"

    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //泛型简化
    fun<T>create(serviceClass: Class<T>):T= retrofit.create(serviceClass)

    //简化
    inline fun <reified T>create():T= create(T::class.java)

}