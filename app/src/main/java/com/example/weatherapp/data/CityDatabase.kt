package com.example.weatherapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cities::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    companion object {
        private var database: CityDatabase? = null
        private val DB_NAME = "cities"
        private val LOCK: Any = Any()

        fun getInstance(context: Context): CityDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance =
                    Room.databaseBuilder(context, CityDatabase::class.java, DB_NAME).allowMainThreadQueries().build()
                database = instance
                return instance
            }
        }
    }
    abstract fun cityDao(): CityDao
}