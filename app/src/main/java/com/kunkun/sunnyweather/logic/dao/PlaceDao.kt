package com.kunkun.sunnyweather.logic.dao

import com.kunkun.sunnyweather.logic.model.Place
import com.tencent.mmkv.MMKV

//  dao 层
object PlaceDao {
    val defaultMMKV = MMKV.defaultMMKV()

    //创建一个mmkv的实例
    fun savePlace(place: Place) {
        defaultMMKV.encode("place", place)
    }
    // 获取保存的place

    fun getPlace(): Place {
        return defaultMMKV.decodeParcelable("place", Place::class.java)!!
    }
    // 检查是否已经保存
    fun isSave()= defaultMMKV.contains("place")
}