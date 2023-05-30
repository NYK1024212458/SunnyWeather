package com.kunkun.sunnyweather.logic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/*
* {
"status": "ok",
"query": "西安",
"places": [
{
"id": "B001D0W888",
"name": "西安市",
"formatted_address": "中国 陕西省 西安市 未央区 未央区",
"location": {
"lat": 34.343207,
"lng": 108.939645
},
"place_id": "a-B001D0W888"
},
{
"id": "B0FFH0XVYP",
"name": "西安饭庄(钟楼店)",
"formatted_address": "中国 陕西省 西安市 碑林区 南大街110号西安钟楼饭店内(钟楼地铁站3号口旁)",
"location": {
"lat": 34.258409,
"lng": 108.946665
},
"place_id": "a-B0FFH0XVYP"
},
{
"id": "B001D08VAO",
"name": "陕西省西安中学",
"formatted_address": "中国 陕西省 西安市 未央区 经开区凤城五路69号",
"location": {
"lat": 34.331756,
"lng": 108.940112
},
"place_id": "a-B001D08VAO"
},
{
"id": "B001D09TZP",
"name": "西安北站",
"formatted_address": "中国 陕西省 西安市 未央区 元朔路",
"location": {
"lat": 34.37666,
"lng": 108.938757
},
"place_id": "a-B001D09TZP"
},
{
"id": "BV10070543",
"name": "西安中学(公交站)",
"formatted_address": "中国 陕西省 西安市 未央区 108路;117路;182路;263路;266路;601路",
"location": {
"lat": 34.332528,
"lng": 108.937665
},
"place_id": "a-BV10070543"
}
]
}
*
* */



data class PlaceResponse(
    val places: List<Place>,
    val query: String,
    val status: String
)
@Parcelize
data class Place(
    @SerializedName("formatted_address")
    val address: String,
    val id: String,
    val location: Location,
    val name: String,
    val place_id: String
):Parcelable

@Parcelize
data class Location(
    val lat: Double,
    val lng: Double
):Parcelable
