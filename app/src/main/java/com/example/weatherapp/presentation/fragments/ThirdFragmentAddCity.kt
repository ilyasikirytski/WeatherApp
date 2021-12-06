package com.example.weatherapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.data.Cities
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import com.example.weatherapp.databinding.FragmentThirdAddCityBinding

class ThirdFragmentAddCity : Fragment() {

    lateinit var binding: FragmentThirdAddCityBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdAddCityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCityButton.setOnClickListener {
            val editTextName = binding.editTextCityName.text.toString().trim()
            if (editTextName.isNotEmpty()){
                setFragmentResult("requestKey", bundleOf("bundleKey" to editTextName))
                viewModel.db.cityDao().insertCity(Cities(editTextName.replaceFirstChar { it.uppercase() }))
                findNavController().navigateUp()
            } else{
                Toast.makeText(context, "Enter city name", Toast.LENGTH_LONG).show()
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThirdFragmentAddCity()
    }
}