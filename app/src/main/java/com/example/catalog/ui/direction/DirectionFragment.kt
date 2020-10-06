package com.example.catalog.ui.direction

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.catalog.R
import com.example.catalog.models.City
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.UiHelper
import io.realm.RealmResults

class DirectionFragment : Fragment() {

    companion object {
        fun newInstance() = DirectionFragment()
    }

    private lateinit var viewModel: DirectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dbService: DatabaseService = DatabaseService()
        val root = inflater.inflate(R.layout.fragment_city, container, false)
        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)
//        dbService.createDummyData()

        val directions: RealmResults<City> = dbService.getAllCities()
        val uiHelper = UiHelper()
        for (direction in directions) {
            val textView = TextView(requireContext())
            val button = uiHelper.createSecondaryButton(requireContext(), direction.title + "direction")
            val bundle = bundleOf(
                "name" to "",
                "phone" to "",
                "direction" to direction.title,
                "city" to arguments?.getString("city", ""),
                "address" to ""
            )
            button.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.search_result, bundle)
            )
            baseLayout.addView(button)

        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DirectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}