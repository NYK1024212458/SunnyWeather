package com.kunkun.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.kunkun.sunnyweather.R
import com.kunkun.sunnyweather.databinding.FragmentPlaceBinding

class PlaceFragment:Fragment() {
    private var _binding: FragmentPlaceBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //lazy 创建viewmodel
    val viemModel by lazy {
        defaultViewModelProviderFactory.create(PlaceViewModel::class.java)
        //过时了,使用上面的替换
//        ViewModelProviders.of(this).get(PlaceViewModel::class.java)
    }
    lateinit var adapter:PlaceAdapter

    //重写的方法
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //设置rv
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.searchRecyclerview.layoutManager=linearLayoutManager
        // 创建adaptr
        adapter=PlaceAdapter(this,viemModel.placeList)
        //
        binding.searchRecyclerview.adapter=adapter
        //输入框进行监听
        binding.searchPlaceEdit.addTextChangedListener {
            val searContent =it.toString()
            if (searContent.isNotEmpty()) viemModel.searchPlace(searContent)
            else{
                binding.searchRecyclerview.visibility=View.GONE
                binding.bgimageview.visibility=View.VISIBLE
                //清除缓存
                viemModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        // 观察livedata
        viemModel.placeLiveData.observe(viewLifecycleOwner, Observer {
            val orNull = it.getOrNull()
            if (orNull!=null){
                binding.searchRecyclerview.visibility=View.VISIBLE
                binding.bgimageview.visibility=View.GONE
                //清除缓存
                viemModel.placeList.clear()
                viemModel.placeList.addAll(orNull)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity," 没有查询到数据 ",Toast.LENGTH_SHORT).show()
                it.exceptionOrNull()?.printStackTrace()
            }


        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}