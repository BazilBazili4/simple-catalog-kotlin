package com.example.catalog.ui.city

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.catalog.R
import com.example.catalog.models.City
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.UiHelper
import io.realm.RealmResults

class CityFragment : Fragment() {

    companion object {
        fun newInstance() = CityFragment()
    }

    private lateinit var viewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dbService: DatabaseService = DatabaseService()
        val root = inflater.inflate(R.layout.fragment_city, container, false)
        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)

//        dbService.createDummyData()
        val cities: RealmResults<City> = dbService.getAllCities()
        val uiHelper = UiHelper()
        for (city in cities) {
            val textView = TextView(requireContext())
            val button = uiHelper.createSecondaryButton(requireContext(), city.title)
            baseLayout.addView(button)

        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}