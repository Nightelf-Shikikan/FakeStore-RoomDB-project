package com.example.roomdbtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdbtest.databinding.ActivityMainBinding
import com.example.roomdbtest.ui.catalog.CatalogActivity


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)







        // Button to open CatalogActivity
        binding.buttonOpenCatalog.setOnClickListener {
            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }


    }
}