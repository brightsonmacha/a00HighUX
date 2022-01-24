package com.example.a00highux

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        toolbar = findViewById(R.id.activity_main_toolbar)
        bottomNav = findViewById(R.id.bottom_nav_view)

        // Get NavHostFragment and NavController
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

        //AppBarConfiguration
        val topDestination = setOf(R.id.fragmentCityList, R.id.fragmentFavoriteList)
        //val appBarConfig = AppBarConfiguration(navController.graph)
        val appBarConfig = AppBarConfiguration(topDestination)

        //ToolBar
        toolbar.setupWithNavController(navController, appBarConfig)

        //NavigationView
        bottomNav.setupWithNavController(navController)
    }
}