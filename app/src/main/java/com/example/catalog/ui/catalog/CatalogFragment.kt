package com.example.catalog.ui.catalog

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
import com.example.catalog.models.Organization
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.UiHelper
import io.realm.RealmResults

class CatalogFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private lateinit var viewModel: CatalogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        viewModel =
            ViewModelProviders.of(this).get(CatalogViewModel::class.java)

        val cardHelper = UiHelper()
        val dbService: DatabaseService = DatabaseService()

        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)
        val organizations: RealmResults<Organization> = dbService.getAllOrganizations()
        for (organization in organizations) {
            baseLayout.addView(
                cardHelper.createCatalogCard(requireContext(), organization)
            )
        }
        baseLayout.addView(cardHelper.createIconLayout(requireContext()))


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}