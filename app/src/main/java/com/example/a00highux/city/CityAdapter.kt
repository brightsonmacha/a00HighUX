package com.example.a00highux.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a00highux.R

class CityAdapter(val context : Context, var cityNames : ArrayList<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        //InflateListItems
        //val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city, parent, false)
        val itemView = LayoutInflater.from(context).inflate(R.layout.grid_item_city, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(cityHolder: CityViewHolder, position: Int) {
        val city = cityNames[position]
        cityHolder.setData(city, position)
    }

    override fun getItemCount(): Int {
        return cityNames.size
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var currentPosition : Int = -1
        private var currentCity : City? = null

        //initializeView
        private var cityImage = itemView.findViewById<ImageView>(R.id.imv_city)
        private var imgDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private var imgFavourite = itemView.findViewById<ImageView>(R.id.imv_favorite)
        private var cityName = itemView.findViewById<TextView>(R.id.txv_city_name)

        //getDrawable
        private var favouriteFilled = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_filled, null)
        private var favouriteBold = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_bordered, null)

        fun setData(city: City, position : Int){
            //
            cityName.text = city.name
            cityImage.setImageResource(city.imageId)
            if(city.isFavorite){
                    imgFavourite.setImageDrawable(favouriteFilled)
            }else{
                imgFavourite.setImageDrawable(favouriteBold)
            }


            this.currentCity = city
            this.currentPosition = position
        }
    }
}