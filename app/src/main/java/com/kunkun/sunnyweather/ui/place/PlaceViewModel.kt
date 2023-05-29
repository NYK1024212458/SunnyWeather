package com.kunkun.sunnyweather.ui.place



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kunkun.sunnyweather.logic.Repository
import com.kunkun.sunnyweather.logic.model.Place

class PlaceViewModel:ViewModel() {
    //创建一个livedata
    private val searchPlace =MutableLiveData<String>()
    //点击搜索place  对应的ff

    // 进行转化 然后观察place的变化
    val placeLiveData= Transformations.switchMap(searchPlace){
        //变化开始执行
        Repository.searchPlace(it)
    }


    fun searchPlace(query:String){
        //保存搜索的数据
        searchPlace.value=query
    }
    // 存放搜索缓存
    val placeList = ArrayList<Place>()
}