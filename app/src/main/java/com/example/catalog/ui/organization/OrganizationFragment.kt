package com.example.catalog.ui.organization

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.catalog.R

class OrganizationFragment : Fragment() {

    companion object {
        fun newInstance() = OrganizationFragment()
    }

    private lateinit var viewModel: OrganizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_organization, container, false)
        val textView = root.findViewById<TextView>(R.id.testId)
        textView.text = arguments?.getLong("organizationId").toString()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrganizationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}