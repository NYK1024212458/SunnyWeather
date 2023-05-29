package com.kunkun.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kunkun.sunnyweather.logic.Repository
import com.kunkun.sunnyweather.logic.model.Location

class WeatherViewModel : ViewModel() {
    //创建一个viewmodel  对应的lng和lat进行查询
    val weatherLiveData = MutableLiveData<Location>()

    //创建一个点击后刷新天气的方法 给livedata赋值
    fun refrushWeather(lng: Double, lat: Double) {
        weatherLiveData.value = Location(lat, lng)
    }

    //对livedata进行转化
    val realWeatherLiveData = Transformations.switchMap(weatherLiveData) {
        Repository.refrushWeather(it.lng, it.lat)
    }

    //
    var locationLng :Double =0.00
    var locationLat :Double =0.00
    var placeName =""
}