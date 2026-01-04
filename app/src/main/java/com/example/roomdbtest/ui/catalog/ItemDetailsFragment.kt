package com.example.roomdbtest.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roomdbtest.data.model.CatalogItem
import com.example.roomdbtest.databinding.FragmentItemDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbtest.viewmodel.CatalogViewModel
import kotlin.apply
import kotlin.jvm.java
import kotlin.let


@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    private var _binding: FragmentItemDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var item: CatalogItem
    private lateinit var viewModel: CatalogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable<CatalogItem>("item")
                ?: throw kotlin.IllegalStateException("Item is missing")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(requireActivity())[CatalogViewModel::class.java]

        binding.itemName.text = item.name
        binding.itemPrice.text = "$${item.price}"

        binding.itemDetailsEdit.setText(item.details)

        binding.saveButton.setOnClickListener {
            // Update item in ViewModel
            val updatedItem = item.copy(details = binding.itemDetailsEdit.text.toString())
            viewModel.updateBasketItem(updatedItem)

            // Close fragment
            parentFragmentManager.popBackStack()
        }



        binding.closeButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(item: CatalogItem) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("item", item)
            }
        }
    }
}