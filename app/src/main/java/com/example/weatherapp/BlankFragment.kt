package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.databinding.FragmentBlankBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.IOException

class BlankFragment : Fragment() {
    private val liveData: WeatherViewModel by viewModels()
    lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater)
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveData.weatherInfo.observe(viewLifecycleOwner) { it ->
            binding.textViewCityName.text = it.name
            binding.textViewDescription.text = it.weather?.joinToString { it.description.toString() }
            binding.textViewTemperature.text = it.main?.temp.toString()
            binding.textViewFeelsLikeValue.text = it.main?.feelsLike.toString()
            binding.textViewTempMinValue.text = it.main?.tempMin.toString()
            binding.textViewTempMaxValue.text = it.main?.tempMax.toString()
            binding.textViewPressureValue.text = it.main?.tempMax.toString()
            binding.textViewHumidityValue.text = it.main?.humidity.toString()
            binding.progressBar.visibility = View.INVISIBLE
        }

        liveData.loadData()

        binding.refreshButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            liveData.loadData()
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}