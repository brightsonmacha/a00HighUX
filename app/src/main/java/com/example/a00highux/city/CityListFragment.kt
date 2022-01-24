package com.example.a00highux.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a00highux.R


class CityListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_city_list, container, false)

        setupRecycleView(view)

        return  view
    }

    private fun setupRecycleView(view: View){
        val context = requireContext()
        val cityAdapter = CityAdapter(context, VacationSpots.cityList!!)

        val recycleView = view.findViewById<RecyclerView>(R.id.city_recycler_view)
        recycleView.adapter = cityAdapter
        recycleView.setHasFixedSize(true)

        //Linear Layout
        //val layoutManager = LinearLayoutManager(context)
        //layoutManager.orientation = RecyclerView.VERTICAL

        //GridLayout
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = RecyclerView.VERTICAL

        //StaggeredLayout
        //val layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)

        recycleView.layoutManager = layoutManager
    }
}