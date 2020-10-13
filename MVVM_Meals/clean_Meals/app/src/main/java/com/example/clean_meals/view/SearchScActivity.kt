package com.example.clean_meals.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.clean_meals.R
import com.example.clean_meals.databinding.ActivitySearchScBinding
import com.example.clean_meals.viewmodel.SearchScViewModel

class SearchScActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchScBinding
    lateinit var viewModel : SearchScViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search_sc)
        viewModel = ViewModelProvider(this)[SearchScViewModel::class.java] //????????????
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}