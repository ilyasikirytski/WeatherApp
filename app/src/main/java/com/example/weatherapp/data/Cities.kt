package com.example.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class Cities(
    @PrimaryKey()
    val cityName: String,
    val latitude: String? = null,
    val longitude: String? = null
    ) {
}
