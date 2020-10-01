package com.example.catalog.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.catalog.R

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
        val textViewCity1: TextView = root.findViewById(R.id.search_param1)
        val textViewCity2: TextView = root.findViewById(R.id.search_param2)
        val textViewCity3: TextView = root.findViewById(R.id.search_param3)
        val textViewCity4: TextView = root.findViewById(R.id.search_param4)
        val textViewCity5: TextView = root.findViewById(R.id.search_param5)

        textViewCity1.text = arguments?.getString("name")
        textViewCity2.text = arguments?.getString("direction")
        textViewCity3.text = arguments?.getString("city")
        textViewCity4.text = arguments?.getString("phone")
        textViewCity5.text = arguments?.getString("address")

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}