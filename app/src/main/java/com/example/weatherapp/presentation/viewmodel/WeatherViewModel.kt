package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.ApiFactory
import com.example.weatherapp.data.CityDatabase
import com.example.weatherapp.presentation.viewmodel.dto.Example
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val db = CityDatabase.getInstance(application)

    private val compositeDisposable = CompositeDisposable()
    var weatherInfo = MutableLiveData<Example>()
    var cityName: String? = null
    var latitude: String? = null
    var longitude: String? = null

    val listOfCities = db.cityDao().getAllCities()

    fun loadData() {
        val disposable = ApiFactory.apiService.getWeather(cityName, latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                weatherInfo.postValue(it)
                Log.d("TEST_OF_LOADING_DATA", "Success: $it")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
            })
        compositeDisposable.addAll(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}