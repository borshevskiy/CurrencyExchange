package com.borshevskiy.currencyexchangetestapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.borshevskiy.currencyexchangetestapp.R
import com.borshevskiy.currencyexchangetestapp.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<FilterFragmentArgs>()
    private var filter = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> getFilter(checkedId) }
        binding.sortButton.setOnClickListener { acceptFilter() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFilter(checkedId: Int) {
        when (checkedId) {
            R.id.name_asc -> filter = "nameAsc"
            R.id.name_desc -> filter = "nameDesc"
            R.id.value_asc -> filter = "valueAsc"
            R.id.value_desc -> filter = "valueDesc"
        }
    }

    private fun acceptFilter() {
        if (args.screenType == "POPULAR") findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToPopularScreen(filter)
        ) else findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToFavoritesScreen(filter)
        )
    }
}