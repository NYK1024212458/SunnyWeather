package com.kunkun.sunnyweather.ui.weather

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kunkun.sunnyweather.R
import com.kunkun.sunnyweather.databinding.ActivityWeatherBinding
import com.kunkun.sunnyweather.databinding.ForecastBinding
import com.kunkun.sunnyweather.databinding.LifeIndexBinding
import com.kunkun.sunnyweather.databinding.NowBinding
import com.kunkun.sunnyweather.logic.model.Weather
import com.kunkun.sunnyweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {
    val TAG=javaClass.name
    //  viewmodel 进行lazy处理
    val weatherViewModel by lazy {
        defaultViewModelProviderFactory.create(WeatherViewModel::class.java)
    }
    lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        // 设置 decorview 实现沉浸式体验  设置decorview的样式
       window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        //设置statusbar的颜色  透明
        window.statusBarColor=Color.TRANSPARENT


        setContentView(binding.root)
//        setContentView(R.layout.activity_weather)


        if (weatherViewModel.locationLng==0.00) {
            weatherViewModel.locationLng = intent.getDoubleExtra("location_lng",0.00)
        }
        if (weatherViewModel.locationLat==0.00) {
            weatherViewModel.locationLat = intent.getDoubleExtra("location_lat",0.00)
        }
        if (weatherViewModel.placeName.isEmpty()) {
            weatherViewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        // 进行livedata的数据的观察

        weatherViewModel.realWeatherLiveData.observe(this, Observer {
            val orNull = it.getOrNull()
            if (orNull != null) {
                //展示ui
                shoeWeatherUI(orNull)
                Log.d(TAG, "onCreate: "+ orNull.toString())
            } else {
                Toast.makeText(this, "无法获取天气信息", Toast.LENGTH_SHORT).show()
                it.exceptionOrNull()?.printStackTrace()
            }

        })

        //调用刷新的
        weatherViewModel.refrushWeather(weatherViewModel.locationLng.toDouble(),weatherViewModel.locationLat.toDouble())


    }

    private fun shoeWeatherUI(weather: Weather) {

        binding.weatherLayoutNow.placeName.text = weatherViewModel.placeName


        val realtime = weather.realtime
        val daily = weather.daily
        // 填充now.xml布局中数据
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        binding.weatherLayoutNow.currentTemp.text = currentTempText
        binding.weatherLayoutNow.currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.air_quality.aqi.chn.toInt()}"
        binding.weatherLayoutNow.currentAQI.text = currentPM25Text
        binding.weatherLayoutNow.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        // 填充forecast.xml布局中的数据
        binding.weatherLayoutForecast.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this)
                .inflate(
                    R.layout.forecast_item,
                    binding.weatherLayoutForecast.forecastLayout,
                    false
                )
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView

            val date = skycon.date.substringBefore("T")

            dateInfo.text = date


            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            binding.weatherLayoutForecast.forecastLayout.addView(view)
        }
        // 填充life_index.xml布局中的数据
        val lifeIndex = daily.life_index
        binding.weatherLayoutlifeIndex.coldRiskText.text = lifeIndex.coldRisk[0].desc
        binding.weatherLayoutlifeIndex.dressingText.text = lifeIndex.dressing[0].desc
        binding.weatherLayoutlifeIndex.ultravioletText.text = lifeIndex.ultraviolet[0].desc
        binding.weatherLayoutlifeIndex.carWashingText.text = lifeIndex.carWashing[0].desc
        binding.weatherLayout.visibility = View.VISIBLE

    }
}