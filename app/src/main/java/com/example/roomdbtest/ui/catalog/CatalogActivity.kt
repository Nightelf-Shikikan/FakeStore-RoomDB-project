package com.example.roomdbtest.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roomdbtest.viewmodel.CatalogViewModel
import kotlinx.coroutines.launch
import com.example.roomdbtest.R

import com.example.roomdbtest.databinding.ActivityCatalogBinding
import com.example.roomdbtest.ui.catalog.CatalogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load both fragments **once**
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CatalogFragment())
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.basketFragmentContainer, BasketFragment())
            .commit()

        // Button actions
        binding.CatalogLoadButton.setOnClickListener {
            viewModel.sendRequest() // loads catalog items into CatalogFragment
        }


    }
}



