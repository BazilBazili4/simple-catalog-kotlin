package com.example.catalog.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.catalog.R
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.SettingsService
import com.google.android.material.textfield.TextInputEditText


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val dbService: DatabaseService = DatabaseService()
        val settingsService: SettingsService = SettingsService(requireContext())
        val defaultCity: String? = settingsService.getDefaultCity()
        val spinnerCity: Spinner = root.findViewById(R.id.spinner_city)
        val cities: ArrayList<String> = dbService.getAllCitiesTitlesAsArray()
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCity.adapter = adapter
        defaultCity.let {
            spinnerCity.setSelection(adapter.getPosition(defaultCity));
        }



        val spinnerDirect: Spinner = root.findViewById(R.id.spinner_direct)
        val directions: ArrayList<String> = dbService.getAllDirectionsTitlesAsArray()

        val adapterDirections: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            directions
        )
        adapterDirections.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDirect.adapter = adapterDirections

        val button: Button = root.findViewById(R.id.search_button)
        val inputPhone: TextInputEditText = root.findViewById(R.id.search_phone_input)
        val inputAddress: TextInputEditText = root.findViewById(R.id.search_address_input)
        val inputName: TextInputEditText = root.findViewById(R.id.search_name_input)



        button.setOnClickListener {
            val bundle = createSearchParams(
                inputName.text.toString(),
                inputPhone.text.toString(),
                spinnerDirect.selectedItem.toString(),
                spinnerCity.selectedItem.toString(),
                inputAddress.text.toString()
            )
            it.findNavController().navigate(R.id.search_result, bundle)
        }

//        button.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.search_result, bundle)
//        )

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun createSearchParams(
        name: String,
        phone: String,
        direction: String,
        city: String,
        address: String
    ): Bundle {
        return bundleOf(
            "name" to name,
            "phone" to phone,
            "direction" to direction,
            "city" to city,
            "address" to address
        )
    }

}