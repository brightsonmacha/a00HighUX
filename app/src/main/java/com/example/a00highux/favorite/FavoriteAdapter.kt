package com.example.a00highux.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a00highux.R
import com.example.a00highux.city.City
import com.example.a00highux.city.VacationSpots


class FavoriteAdapter(val context: Context, private var favoriteCity: MutableList<City>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(favoriteHolder: FavoriteViewHolder, position: Int) {
        val city = favoriteCity[position]
        favoriteHolder.setData(city, position)
        favoriteHolder.setListener()
    }

    override fun getItemCount(): Int {
        return favoriteCity.size
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var currentPosition: Int = -1
        private var currentCity: City? = null

        //initializeView
        private var cityImage = itemView.findViewById<ImageView>(R.id.imv_city_fav)
        private var imgDelete = itemView.findViewById<ImageView>(R.id.imv_delete_fav)
        private var imgFavourite = itemView.findViewById<ImageView>(R.id.imv_favorite_fav)
        private var cityName = itemView.findViewById<TextView>(R.id.txv_city_fav)

        //getDrawable
        private var favouriteFilled =
            ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_filled, null)
        private var favouriteBold =
            ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_bordered, null)

        fun setData(city: City, position: Int) {
            cityName.text = city.name
            cityImage.setImageResource(city.imageId)
            if (city.isFavorite) {
                imgFavourite.setImageDrawable(favouriteFilled)
            } else {
                imgFavourite.setImageDrawable(favouriteBold)
            }

            this.currentCity = city
            this.currentPosition = position
        }

        fun setListener(){
            imgDelete.setOnClickListener(this)
            imgFavourite.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.imv_delete_fav -> deleteCity()
                R.id.imv_favorite_fav -> addFavorite()
            }
        }

        private fun deleteCity(){
            favoriteCity.removeAt(currentPosition)
            notifyItemRemoved(currentPosition)
            notifyItemRangeChanged(currentPosition, favoriteCity.size)

            VacationSpots.cityList?.remove(currentCity)
        }

        private fun addFavorite(){
            currentCity?.isFavorite = !(currentCity?.isFavorite!!)
            if(currentCity?.isFavorite!!){
                imgFavourite.setImageDrawable(favouriteFilled)
                VacationSpots.favoriteCityList.add(currentCity!!)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, favoriteCity.size)
            }else{
                imgFavourite.setImageDrawable(favouriteBold)
                VacationSpots.favoriteCityList.remove(currentCity!!)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, favoriteCity.size)
            }
        }
    }
}