package com.kunkun.sunnyweather.logic.model

data class Weather(val realtime: RealTimeResponse.Realtime,val daily: DailyResponse.Daily) {
}