package com.example.photographer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.photographer.fragments.BookingFragment
import com.example.photographer.fragments.AvailibilityFragment
import com.example.photographer.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private val availibilityFragment = AvailibilityFragment()
    private val bookingFragment = BookingFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(profileFragment)

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_availibility -> replaceFragment(availibilityFragment)
                R.id.navigation_bookings -> replaceFragment(bookingFragment)
                R.id.navigation_profile -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transection = supportFragmentManager.beginTransaction()
            transection.replace(R.id.fragment_container,fragment)
            transection.commit()
        }
    }
}


