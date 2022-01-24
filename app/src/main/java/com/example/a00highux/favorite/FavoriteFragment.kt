package com.example.a00highux.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a00highux.R
import com.example.a00highux.city.VacationSpots


class FavoriteFragment : Fragment() {

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
        val favoriteAdapter = FavoriteAdapter(
            context,
            VacationSpots.favoriteCityList
        )

        val recycleView = view.findViewById<RecyclerView>(R.id.city_recycler_view_fav)
        recycleView.adapter = favoriteAdapter
        recycleView.setHasFixedSize(true)

        val layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        //layoutManager.orientation = RecyclerView.VERTICAL
        recycleView.layoutManager = layoutManager
    }
}
