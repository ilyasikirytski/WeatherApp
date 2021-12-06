package com.example.weatherapp.domain

import com.example.weatherapp.presentation.viewmodel.dto.Example
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?appid=029550dd49cd1eaaced588a5d960b10f&units=metric")
    fun getWeather(@Query("q") cityName: String?,
                   @Query("lat") latitude: String?,
                   @Query("lon") longitude: String?): Single<Example>

//    api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
//  "weather?lat={lat}&lon={lon}&appid={API key}"
//    @GET("weather?appid=029550dd49cd1eaaced588a5d960b10f&units=metric")
//    fun getWeather(@Query("lat") latitude: String?, @Query("lon") longitude: String?): Single<Example>

}