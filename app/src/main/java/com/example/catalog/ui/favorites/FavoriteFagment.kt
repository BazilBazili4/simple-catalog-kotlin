package com.example.catalog.ui.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.catalog.R
import com.example.catalog.models.Organization
import com.example.catalog.services.DatabaseService
import com.example.catalog.services.UiHelper
import com.example.catalog.ui.catalog.CatalogViewModel
import io.realm.RealmResults

class FavoriteFagment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFagment()
    }

    private lateinit var viewModel: FavoriteFagmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        viewModel =
            ViewModelProviders.of(this).get(FavoriteFagmentViewModel::class.java)

        val cardHelper = UiHelper()
        val dbService: DatabaseService = DatabaseService()

        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)
        val organizations: RealmResults<Organization> = dbService.getFavoritesOrganizations()
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
        viewModel = ViewModelProviders.of(this).get(FavoriteFagmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}