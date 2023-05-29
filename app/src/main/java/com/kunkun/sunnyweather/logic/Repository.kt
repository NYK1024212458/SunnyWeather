package com.kunkun.sunnyweather.logic

import androidx.lifecycle.liveData
import com.kunkun.sunnyweather.logic.model.DailyResponse
import com.kunkun.sunnyweather.logic.model.Place
import com.kunkun.sunnyweather.logic.model.Weather
import com.kunkun.sunnyweather.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

//仓库层,判断数据的来源是网络还是本地缓存
//直接返回一个livedata   liveData(Dispatchers.IO) {// 会自动构建一个livedata对象,提供一个挂起函数的上下文,在这里可以调用挂起函数
//   最后的结果通过Kotlin内置的函数进行包装返回.
//  最后记得使用emit()将数据发送出去,类似与调用了livedate的setvalue方法
// }
object Repository {
    // 获取位置place
    fun searchPlace(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlace(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                //
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is {placeResponse.status}"))
            }


        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    // 获取对应lng.lat 的天气信息
    fun refrushWeather(lng: Double, lat: Double) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferrealTimeResponse = async {
                    SunnyWeatherNetWork.getRealTimeWeather(lng, lat)

                }

                val deferdailyWeatherResponse = async {
                    SunnyWeatherNetWork.getDailyWeather(lng, lat)
                }
               //调用awiat(0
              val realTimeResponse=  deferrealTimeResponse.await()
              val dailyWeatheResponser=  deferdailyWeatherResponse.await()
                if (realTimeResponse.status=="ok"&&dailyWeatheResponser.status=="ok"){
                    //  组合数据Weather
                    val  weather =Weather(realTimeResponse.result.realtime,dailyWeatheResponser.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(RuntimeException("realTimeResponse is status ${realTimeResponse.status} "+ "dailyWeatheResponser is status ${dailyWeatheResponser.status}"))
                }


            }


        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }
}