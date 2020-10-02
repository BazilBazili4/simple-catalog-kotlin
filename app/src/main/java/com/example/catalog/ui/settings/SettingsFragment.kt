package com.example.catalog.ui.settings

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.catalog.R

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_settings, container, false)
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

        spinnerCity?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val defaulValue = "Муниципалитет"
                val selectedValue = spinnerCity.selectedItem.toString()
                if (!defaulValue.equals(selectedValue)) {
                    val ad = AlertDialog.Builder(requireContext()).create()
                    ad.setMessage("Муниципалитет по умолчанию: $selectedValue")
                    ad.setCancelable(true)
                    ad.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val  cacheButton: Button = root.findViewById(R.id.cache_button)
        cacheButton.setOnClickListener {
            val ad = AlertDialog.Builder(requireContext()).create()
            ad.setMessage("Кэш очищен")
            ad.setCancelable(true)
            ad.show()
        }

        val  fefreshButton: Button = root.findViewById(R.id.refresh_button)
        fefreshButton.setOnClickListener {
            val ad = AlertDialog.Builder(requireContext()).create()
            ad.setMessage("Данные обновлены")
            ad.setCancelable(true)
            ad.show()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}