package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CityDao {
//    ORDER BY cityName ASC
    @Query("SELECT cityName FROM cities ORDER BY case when cityName = 'Current location' then 0 else 1 end, cityName")
    fun getAllCities() : LiveData<MutableList<Cities>>

    @Insert(onConflict = REPLACE)
    fun insertCity(city: Cities)

    @Delete
    fun deleteCity(city: Cities)
}