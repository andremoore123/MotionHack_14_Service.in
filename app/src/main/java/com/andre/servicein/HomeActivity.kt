package com.andre.servicein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostMainFragment =  supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navHostMainController = navHostMainFragment.navController
        findViewById<BottomNavigationView>(R.id.home_bottom_nav)
            .setupWithNavController(navHostMainController)

    }
}