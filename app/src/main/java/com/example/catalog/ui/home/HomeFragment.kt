package com.example.catalog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.catalog.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val buttonToSearch: Button = root.findViewById(R.id.to_search_button)
        buttonToSearch.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.navigation_search, null))

        val buttonToList: Button = root.findViewById(R.id.to_list_button)
        buttonToList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.navigation_list, null))

        return root
    }
}