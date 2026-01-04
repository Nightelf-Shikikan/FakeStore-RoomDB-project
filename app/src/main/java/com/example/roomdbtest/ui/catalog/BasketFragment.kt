package com.example.roomdbtest.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbtest.databinding.FragmentBasketBinding
import com.example.roomdbtest.viewmodel.CatalogViewModel
import kotlinx.coroutines.launch

class BasketFragment : Fragment() {

    private val viewModel: CatalogViewModel by activityViewModels()
    private lateinit var adapter: BasketAdapter

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!  // safe non-null reference

    private val basketRecyclerView get() = binding.basketRecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)

        adapter = BasketAdapter(emptyList()) { item ->
            viewModel.removeFromBasket(item)
        }

        basketRecyclerView.adapter = adapter
        basketRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeBasket()

        return binding.root
    }

    private fun observeBasket() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.basket.collect { items ->
                    adapter.updateItems(items)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}