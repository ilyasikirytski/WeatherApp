package com.example.weatherapp

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.databinding.FragmentBlankBinding
import com.example.weatherapp.pojo.Example
import com.example.weatherapp.pojo.Main
import com.example.weatherapp.pojo.Weather
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var weatherInfo = MutableLiveData<Example>()
    var cityName: String = "minsk"

    fun loadData() {
        val disposable = ApiFactory.apiService.getWeather(cityName)
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

//    private fun getWeather(example: Example): List<Weather> {
//        val result = ArrayList<Weather>()
//        val jsonObject = example ?: return result
//        val coinKeySet = jsonObject.keySet()
//        for (coinKey in coinKeySet) {
//            val currencyJson = jsonObject.getAsJsonObject(coinKey)
//            val currencyKeySet = currencyJson.keySet()
//            for (currencyKey in currencyKeySet) {
//                val priceInfo = Gson().fromJson(
//                    currencyJson.getAsJsonObject(currencyKey),
//                    Example::class.java
//                )
//                result.add(priceInfo)
//            }
//        }
//        return result
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}