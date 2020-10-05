package com.example.catalog.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.catalog.R
import com.example.catalog.models.Organization
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.UiHelper
import com.example.catalog.ui.catalog.CatalogViewModel
import io.realm.RealmResults

class SearchResultFragment : Fragment() {

    companion object {
        fun newInstance() = SearchResultFragment()
    }

    private lateinit var viewModel: SearchResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search_result, container, false)

        viewModel =
            ViewModelProviders.of(this).get(SearchResultViewModel::class.java)

        val cardHelper = UiHelper()
        val dbService: DatabaseService = DatabaseService()

        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)
        val organizations: RealmResults<Organization> = dbService.getOrganizationsByParams(
            arguments?.getString("city", ""),
            arguments?.getString("direction", ""),
            arguments?.getString("phone", ""),
            arguments?.getString("address", ""),
            arguments?.getString("name", "")
        )
        for (organization in organizations) {
            baseLayout.addView(
                cardHelper.createCatalogCard(requireContext(), organization, dbService)
            )
        }
        baseLayout.addView(cardHelper.createIconLayout(requireContext()))

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}