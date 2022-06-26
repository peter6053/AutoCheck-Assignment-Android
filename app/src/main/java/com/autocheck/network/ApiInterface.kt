package com.autocheck.network

import com.autocheck.model.CarListModel.CarItemModel
import com.autocheck.model.CarMediaModel.CarMediaModel
import com.autocheck.model.popularModel.PopularItemModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("make?popular=true")
    fun getPopulars() : Call<PopularItemModel>

    @GET("car/search")
    fun getAllCars() : Call<CarItemModel>

    @GET("car_media")
    fun getCarMedia(@Query(value = "carId",encoded=true) string: String) : Call<CarMediaModel>

    companion object {
        var BASE_URL = "https://api.staging.myautochek.com/v1/inventory/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}