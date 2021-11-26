package com.example.weatherapp.api

import com.example.weatherapp.Cities
import com.example.weatherapp.pojo.Example
import com.example.weatherapp.pojo.Main
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?appid=029550dd49cd1eaaced588a5d960b10f&units=metric")
    fun getWeather(@Query("q") cityName: String): Single<Example>

//    @GET("weather?q=minsk&appid=029550dd49cd1eaaced588a5d960b10f&units=metric")
//    fun getCities(@Query("minsk") cityName: String): Single<Example>

}