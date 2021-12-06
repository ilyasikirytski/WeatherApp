package com.example.weatherapp.presentation.viewmodel.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Example(
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null,
    @SerializedName("main")
    @Expose
    val main: Main? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)