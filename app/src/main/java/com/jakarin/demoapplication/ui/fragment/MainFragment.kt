package com.jakarin.demoapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jakarin.demoapplication.R
import com.jakarin.demoapplication.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.id.main_fragment) {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.indicesButton.apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_mainFragment_to_indicesFragment)
            }
        }
        binding.portfolioButton.apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_mainFragment_to_portfolioFragment)
            }
        }
    }
}