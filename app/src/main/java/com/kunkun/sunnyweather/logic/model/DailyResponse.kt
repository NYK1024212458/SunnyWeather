package com.kunkun.sunnyweather.logic.model

data class DailyResponse(
    val api_status: String,
    val api_version: String,
    val lang: String,
    val location: List<Double>,
    val result: Result,
    val server_time: Int,
    val status: String,
    val timezone: String,
    val tzshift: Int,
    val unit: String
) {

    data class Result(
        val daily: Daily,
        val primary: Int
    )

    data class Daily(
        val air_quality: AirQuality,
        val astro: List<Astro>,
        val cloudrate: List<Cloudrate>,
        val dswrf: List<Dswrf>,
        val humidity: List<Humidity>,
        val life_index: LifeIndex,
        val precipitation: List<Precipitation>,
        val precipitation_08h_20h: List<Precipitation08h20h>,
        val precipitation_20h_32h: List<Precipitation20h32h>,
        val pressure: List<Pressure>,
        val skycon: List<Skycon>,
        val skycon_08h_20h: List<Skycon08h20h>,
        val skycon_20h_32h: List<Skycon20h32h>,
        val status: String,
        val temperature: List<Temperature>,
        val temperature_08h_20h: List<Temperature08h20h>,
        val temperature_20h_32h: List<Temperature20h32h>,
        val visibility: List<Visibility>,
        val wind: List<Wind>,
        val wind_08h_20h: List<Wind08h20h>,
        val wind_20h_32h: List<Wind20h32h>
    )

    data class AirQuality(
        val aqi: List<Aqi>,
        val pm25: List<Pm25>
    )

    data class Astro(
        val date: String,
        val sunrise: Sunrise,
        val sunset: Sunset
    )

    data class Cloudrate(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Dswrf(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Humidity(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class LifeIndex(
        val carWashing: List<CarWashing>,
        val coldRisk: List<ColdRisk>,
        val comfort: List<Comfort>,
        val dressing: List<Dressing>,
        val ultraviolet: List<Ultraviolet>
    )

    data class Precipitation(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Int,
        val probability: Int
    )

    data class Precipitation08h20h(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Int,
        val probability: Int
    )

    data class Precipitation20h32h(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double,
        val probability: Int
    )

    data class Pressure(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Skycon(
        val date: String,
        val value: String
    )

    data class Skycon08h20h(
        val date: String,
        val value: String
    )

    data class Skycon20h32h(
        val date: String,
        val value: String
    )

    data class Temperature(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Temperature08h20h(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Temperature20h32h(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Visibility(
        val avg: Double,
        val date: String,
        val max: Double,
        val min: Double
    )

    data class Wind(
        val avg: AvgX,
        val date: String,
        val max: MaxX,
        val min: MinX
    )

    data class Wind08h20h(
        val avg: AvgX,
        val date: String,
        val max: MaxXX,
        val min: MinXX
    )

    data class Wind20h32h(
        val avg: AvgX,
        val date: String,
        val max: MaxXXX,
        val min: MinXXX
    )

    data class Aqi(
        val avg: Avg,
        val date: String,
        val max: Max,
        val min: Min
    )

    data class Pm25(
        val avg: Int,
        val date: String,
        val max: Int,
        val min: Int
    )

    data class Avg(
        val chn: Int,
        val usa: Int
    )

    data class Max(
        val chn: Int,
        val usa: Int
    )

    data class Min(
        val chn: Int,
        val usa: Int
    )

    data class Sunrise(
        val time: String
    )

    data class Sunset(
        val time: String
    )

    data class CarWashing(
        val date: String,
        val desc: String,
        val index: String
    )

    data class ColdRisk(
        val date: String,
        val desc: String,
        val index: String
    )

    data class Comfort(
        val date: String,
        val desc: String,
        val index: String
    )

    data class Dressing(
        val date: String,
        val desc: String,
        val index: String
    )

    data class Ultraviolet(
        val date: String,
        val desc: String,
        val index: String
    )

    data class AvgX(
        val direction: Double,
        val speed: Double
    )

    data class MaxX(
        val direction: Double,
        val speed: Double
    )

    data class MaxXXX(
        val direction: Double,
        val speed: Double
    )

    data class MaxXX(
        val direction: Double,
        val speed: Double
    )

    data class MinX(
        val direction: Double,
        val speed: Double
    )

    data class MinXXX(
        val direction: Double,
        val speed: Double
    )

    data class MinXX(
        val direction: Double,
        val speed: Double
    )
}