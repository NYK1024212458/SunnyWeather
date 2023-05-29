package com.kunkun.sunnyweather.logic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

     //添加拦截器

    val logging= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient= OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    //  添加baseurl
    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //泛型简化
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //简化
    inline fun <reified T> create(): T = create(T::class.java)

}