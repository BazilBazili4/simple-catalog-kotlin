package com.example.catalog.ui.catalog

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.catalog.R
import com.example.catalog.services.UiHelper

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
        val baseLayout: LinearLayout = root.findViewById(R.id.base_layout)
        for(i in 1..5){
            baseLayout.addView(cardHelper.createCatalogCard(requireContext()))
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