package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    @Expose
    val temp: Double? = null,

    @SerializedName("feels_like")
    @Expose
    val feelsLike: Double? = null,

    @SerializedName("temp_min")
    @Expose
    val tempMin: Double? = null,

    @SerializedName("temp_max")
    @Expose
    val tempMax: Double? = null,

    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,

    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,
)