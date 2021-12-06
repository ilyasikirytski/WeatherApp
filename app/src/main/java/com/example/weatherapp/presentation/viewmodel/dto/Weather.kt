package com.example.weatherapp.presentation.viewmodel.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    @Expose
    val description: String? = null
)