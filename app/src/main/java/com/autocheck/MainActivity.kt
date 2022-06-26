package com.autocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autocheck.adapter.CarAdapter
import com.autocheck.adapter.PopularAdapter
import com.autocheck.model.CarListModel.CarItemModel
import com.autocheck.model.CarListModel.Result
import com.autocheck.model.CarListModel.Stats
import com.autocheck.model.popularModel.Make
import com.autocheck.model.popularModel.PopularItemModel
import com.autocheck.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private var popularRV : RecyclerView ? = null
    private var carRV : RecyclerView ? = null
    private var popularList:ArrayList<Make> ? = null
    private var carList:ArrayList<com.autocheck.model.CarListModel.Result> ? = null
    private var popularAdapter:PopularAdapter ? = null
    private var carAdapter:CarAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        popularRV = findViewById<RecyclerView>(R.id.popular_rv)
        popularRV?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        popularList = ArrayList<Make>()
        popularAdapter = PopularAdapter(popularList!!)
        popularRV?.adapter = popularAdapter

        loadPopularData()

        carRV = findViewById<RecyclerView>(R.id.list_rv)
        carRV?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        carList = ArrayList<Result>()
        carAdapter = CarAdapter(carList!!)
        carRV?.adapter = carAdapter
        loadAllCars()
    }

    fun loadPopularData() {
        val apiInterface = ApiInterface.create().getPopulars()
        apiInterface.enqueue( object : Callback<PopularItemModel> {
            override fun onResponse(call: Call<PopularItemModel>, response: Response<PopularItemModel>) {
                if(response.body() != null) {
                    popularList?.addAll(response.body()!!.makeList)
                    popularAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<PopularItemModel>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
        })
    }

    fun loadAllCars() {
        val apiInterface = ApiInterface.create().getAllCars()
        apiInterface.enqueue( object : Callback<CarItemModel> {
            override fun onResponse(call: Call<CarItemModel>, response: Response<CarItemModel>) {
                if(response.body() != null) {
                    for ((index, item) in response.body()!!.result.withIndex()) {
                        carList?.add(item)
                        carAdapter?.notifyItemInserted(index)
                    }
                }
            }

            override fun onFailure(call: Call<CarItemModel>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
                Toast.makeText(applicationContext, "Error: "+t.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
}

