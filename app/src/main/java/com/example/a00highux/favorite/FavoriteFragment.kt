package com.example.a00highux.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a00highux.R
import com.example.a00highux.city.City
import com.example.a00highux.city.VacationSpots
import com.google.android.material.snackbar.Snackbar
import java.util.*


class FavoriteFragment : Fragment() {
    lateinit var recycleView: RecyclerView
    lateinit var favoriteCity: MutableList<City>
    lateinit var favoriteAdapter: FavoriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        setupRecycleView(view)
        return view
    }

    private fun setupRecycleView(view: View) {
        val context = requireContext()
        favoriteCity = VacationSpots.favoriteCityList

        favoriteAdapter = FavoriteAdapter(
            context,
            favoriteCity
        )

        recycleView = view.findViewById(R.id.city_recycler_view_fav)
        recycleView.adapter = favoriteAdapter
        recycleView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recycleView.layoutManager = layoutManager
        itemTouchHelper.attachToRecyclerView(recycleView)
    }

    private val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                Collections.swap(favoriteCity, fromPosition, toPosition)
                recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deleteCity = favoriteCity[position]
                deleteCityItem(position)
                updateCityList(deleteCity, false)

                Snackbar.make(recycleView, "Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        undoDelete(position, deleteCity)
                        updateCityList(deleteCity, true)
                    }
                    .show()
            }

        })

    private fun undoDelete(position: Int, city: City) {
        favoriteCity.add(position, city)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteCity.size)
    }

    private fun deleteCityItem(currentPosition: Int) {
        favoriteCity.removeAt(currentPosition)
        favoriteAdapter.notifyItemRemoved(currentPosition)
        favoriteAdapter.notifyItemRangeChanged(currentPosition, favoriteCity.size)
    }

    private fun updateCityList(city: City, isFavorite: Boolean) {
        val cityList = VacationSpots.cityList!!
        val position = cityList.indexOf(city)
        cityList[position].isFavorite = isFavorite
    }
}
