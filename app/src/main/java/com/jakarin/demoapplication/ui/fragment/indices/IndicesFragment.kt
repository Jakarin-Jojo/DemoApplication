package com.jakarin.demoapplication.ui.fragment.indices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.jakarin.demoapplication.R
import com.jakarin.demoapplication.adapter.IndicesAdapter
import com.jakarin.demoapplication.databinding.FragmentIndicesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IndicesFragment : Fragment(R.layout.fragment_indices) {
    private lateinit var binding: FragmentIndicesBinding
    private val indicesViewModel: IndicesViewModel by viewModels()

    @Inject
    lateinit var indicesAdapter: IndicesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndicesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerViewIndices.apply {
                adapter = indicesAdapter
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL,
                    )
                )
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.HORIZONTAL,
                    )
                )
            }
            imageViewClose.setOnClickListener {
                findNavController().navigate(R.id.action_indices_fragment_to_main_fragment)
            }
        }
        observeIndicesData()
        observeLastUpdate()
    }

    private fun observeIndicesData() {
        indicesViewModel.indicesLiveData.observe(viewLifecycleOwner) { uiState ->
            when {
                uiState.isLoading -> showLoading()
                uiState.error != null -> {
                    hideLoading()
                    binding.textVieWError.text = getString(R.string.error_message_general)
                    Toast.makeText(context, uiState.error.toString(), Toast.LENGTH_LONG).show()
                }

                uiState.data != null -> {
                    hideLoading()
                    binding.textVieWError.text = ""
                    indicesAdapter.submitList(uiState.data.data)
                }
            }
        }
    }

    private fun observeLastUpdate() {
        indicesViewModel.lastUpdateLiveData.observe(viewLifecycleOwner) { lastUpdate ->
            binding.textViewLastUpdate.text = lastUpdate
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}