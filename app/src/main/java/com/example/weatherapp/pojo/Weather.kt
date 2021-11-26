package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    @Expose
    val description: String? = null
)