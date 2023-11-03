package com.example.weatherapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.Cities
import com.example.weatherapp.databinding.OneCityItemBinding

class CityAdapter(private val context: Context?) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
 
    var cityItemList: List<Cities> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CityViewHolder(binding: OneCityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var cityItem = binding.textViewCity
        var cardView = binding.cardView
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(OneCityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityItem = cityItemList[position]
        holder.cityItem.text = cityItem.cityName
        holder.cardView.setOnClickListener {
            onItemClickListener?.onItemClick(cityItem)

        }
    }

    override fun getItemCount(): Int {
        return cityItemList.size
    }

    interface OnItemClickListener {
        fun onItemClick(cityInfo: Cities)
    }
}

