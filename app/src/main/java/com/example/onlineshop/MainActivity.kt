package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.onlineshop.cart.CartFragment
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.favorite.FavoriteFragment
import com.example.onlineshop.home.HomeFragment
import com.example.onlineshop.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.bottomNav.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.actionHome ->{
                    findNavController(R.id.fragment_container).navigate(R.id.homeFragment)
                }
                R.id.actionCart ->{
                    findNavController(R.id.fragment_container).navigate(R.id.cartFragment)
                }
                R.id.actionFavorite ->{
                    findNavController(R.id.fragment_container).navigate(R.id.favoriteFragment)
                }
                R.id.actionProfile ->{
                    findNavController(R.id.fragment_container).navigate(R.id.profileFragment)
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
}