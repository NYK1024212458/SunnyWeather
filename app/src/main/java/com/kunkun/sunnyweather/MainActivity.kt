package com.kunkun.sunnyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunkun.sunnyweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

    }
}