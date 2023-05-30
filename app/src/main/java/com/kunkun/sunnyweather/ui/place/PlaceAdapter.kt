package com.kunkun.sunnyweather.ui.place

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kunkun.sunnyweather.R
import com.kunkun.sunnyweather.logic.model.Place
import com.kunkun.sunnyweather.ui.weather.WeatherActivity

class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 获取控件
        val placename = itemView.findViewById<TextView>(R.id.placename)
        val placeaddress = itemView.findViewById<TextView>(R.id.placeaddress)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //创建布局view
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        val viewHolder = ViewHolder(inflate)
        viewHolder.itemView.setOnClickListener {

            val absoluteAdapterPosition = viewHolder.absoluteAdapterPosition
            val place = placeList[absoluteAdapterPosition]
            val placeFragment = fragment as PlaceFragment
            placeFragment.viemModel.savePlace(place)
            val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            fragment.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //获取postion的数据
        val place = placeList[position]
        //赋值给viewm
        holder.placename.text = place.name
        holder.placeaddress.text = place.address
    }

    override fun getItemCount(): Int {
        return placeList.size
    }


}