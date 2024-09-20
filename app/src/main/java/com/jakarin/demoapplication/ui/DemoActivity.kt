package com.jakarin.demoapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jakarin.demoapplication.R
import com.jakarin.demoapplication.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}