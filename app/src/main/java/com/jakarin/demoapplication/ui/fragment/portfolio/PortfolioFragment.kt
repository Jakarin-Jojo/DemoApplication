package com.jakarin.demoapplication.ui.fragment.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakarin.demoapplication.R
import com.jakarin.demoapplication.adapter.PortfolioAdapter
import com.jakarin.demoapplication.databinding.FragmentPortfolioBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {
    private lateinit var binding: FragmentPortfolioBinding
    private val portfolioViewModel: PortfolioViewModel by viewModels()

    @Inject
    lateinit var portfolioAdapter: PortfolioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPortfolioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerViewPortfolio.apply {
                adapter = portfolioAdapter
                layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            imageViewClose.setOnClickListener {
                findNavController().navigate(R.id.action_portfolio_fragment_to_main_fragment)
            }
        }
        observePortfolio()
    }

    private fun observePortfolio() {
        portfolioViewModel.portfolioUiState.observe(viewLifecycleOwner) { uiState ->
            when {
                uiState.isLoading -> {
                    showLoading()
                }

                uiState.error != null -> {
                    hideLoading()
                    binding.textVieWError.text = getString(R.string.error_message_general)
                    Toast.makeText(context, uiState.error.toString(), Toast.LENGTH_LONG).show()
                }

                uiState.data != null -> {
                    hideLoading()
                    portfolioAdapter.submitList(uiState.data)
                    binding.textVieWError.text = ""
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}