package com.example.catalog.ui.catalog

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.catalog.R
import com.example.catalog.ui.home.HomeViewModel

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

        val textView: TextView = root.findViewById(R.id.greeting_text)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val secondTextView: TextView = root.findViewById(R.id.second_text)
        viewModel.secondField.observe(viewLifecycleOwner, Observer {
            secondTextView.text = it
        })

        val button: TextView = root.findViewById(R.id.button)
        button.setOnClickListener { viewModel.updateGreet(true, "fuck") }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}