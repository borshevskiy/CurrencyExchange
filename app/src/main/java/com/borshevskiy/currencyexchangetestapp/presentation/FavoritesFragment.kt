package com.borshevskiy.currencyexchangetestapp.presentation

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.borshevskiy.currencyexchangetestapp.R
import com.borshevskiy.currencyexchangetestapp.databinding.FragmentFavoritesBinding
import com.borshevskiy.currencyexchangetestapp.presentation.adapter.FavCurrencyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter by lazy { FavCurrencyAdapter(mainViewModel) }

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        preferences = requireActivity().getSharedPreferences("app_settings", MODE_PRIVATE)
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCurrencyList.adapter = mAdapter
        readDatabase()
        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                backupFavorites()
                val list = preferences.getString("FAVORITES", "")
                if (!list.isNullOrEmpty()) {
                    mainViewModel.getFavoriteCurrencies(parent.getItemAtPosition(position).toString().trim(), list)
                }
            }
        binding.filterFab.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionFavoritesScreenToFilterFragment(
                "FAVORITES"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.readFavoriteCurrencies.collect { database ->
                if (database.isNotEmpty()) {
                    mAdapter.submitList(database)
                }
                binding.autoCompleteTextView.setAdapter(ArrayAdapter(requireContext(),
                    R.layout.dropdown_item,
                    preferences.getString("CurrencyNames", "")!!.split(",")))
            }
        }
    }

    private fun backupFavorites() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.readFavoriteCurrencies.collect { database ->
                if (database.isNotEmpty()) {
                    val list = StringBuilder()
                    database.forEach { list.append("${it.name},") }
                    preferences.edit().putString("FAVORITES", list.toString().removeSuffix(","))
                        .apply()
                }
            }
        }
    }
}