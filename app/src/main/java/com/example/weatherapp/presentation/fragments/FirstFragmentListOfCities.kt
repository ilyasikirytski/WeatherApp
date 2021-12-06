package com.example.weatherapp.presentation.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.Cities
import com.example.weatherapp.R
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import com.example.weatherapp.presentation.adapter.CityAdapter
import com.example.weatherapp.databinding.FragmentFirstListOfCitiesBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class FirstFragmentListOfCities : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var binding: FragmentFirstListOfCitiesBinding
    val adapter = CityAdapter(context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        getLocation(false)

        binding = FragmentFirstListOfCitiesBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.listOfCities.observe(this, Observer {
            adapter.cityItemList = it
        })

        viewModel.db.cityDao().insertCity(Cities("Current location"))
        binding.recyclerView.adapter = adapter
        adapter.onItemClickListener = object : CityAdapter.OnItemClickListener {
            override fun onItemClick(cityInfo: Cities) {
                if (cityInfo.cityName == "Current location") {
                    getLocation()
                } else {
                    val bundle = bundleOf("key" to cityInfo.cityName)
                    findNavController().navigate(
                        R.id.action_firstFragmentListOfCities_to_secondFragmentCityInfo,
                        bundle
                    )
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragmentListOfCities_to_thirdFragmentAddCity)
        }
    }

    private fun getLocation() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this.requireActivity())
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            Toast.makeText(context, "${it.latitude}    ${it.longitude}", Toast.LENGTH_LONG)
                .show()
            viewModel.db.cityDao().insertCity(
                Cities("Current location", it.latitude.toString(), it.longitude.toString())
            )

            val bundle = bundleOf(
                "latitude" to it.latitude.toString(),
                "longitude" to it.longitude.toString()
            )
            findNavController().navigate(
                R.id.action_firstFragmentListOfCities_to_secondFragmentCityInfo,
                bundle
            )
        }
        task.addOnFailureListener {
            Log.d("TESTtt", it.message.toString())
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragmentListOfCities()
    }
}