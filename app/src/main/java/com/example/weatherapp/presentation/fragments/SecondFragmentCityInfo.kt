package com.example.weatherapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import com.example.weatherapp.databinding.FragmentSecondCityInfoBinding

class SecondFragmentCityInfo : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()
    lateinit var binding: FragmentSecondCityInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondCityInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherInfo.observe(viewLifecycleOwner) { it ->
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

        viewModel.latitude = arguments?.getString("latitude")
        viewModel.longitude = arguments?.getString("longitude")
        viewModel.cityName = arguments?.getString("key")
        viewModel.loadData()


        binding.refreshButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadData()
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SecondFragmentCityInfo()
    }
}

