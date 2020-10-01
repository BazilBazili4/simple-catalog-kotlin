package com.example.catalog.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.catalog.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


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
        val spinnerCity: Spinner = root.findViewById(R.id.spinner_city)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.brew_array,
            R.layout.spinner
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCity.adapter = adapter
        }

        val spinnerDirect: Spinner = root.findViewById(R.id.spinner_direct)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.brew_array2,
            R.layout.spinner
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerDirect.adapter = adapter
        }

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

    fun createSearchParams(name: String, phone: String, direction: String, city: String, address: String): Bundle {
        return bundleOf(
            "name" to name,
            "phone" to phone,
            "direction" to direction,
            "city" to city,
            "address" to address
        )
    }

}