package com.example.clean_meals.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clean_meals.R
import com.example.clean_meals.databinding.BreakfastFragmentBinding
import com.example.clean_meals.databinding.LunchFragmentBinding
import com.example.clean_meals.viewmodel.BreakfastViewModel
import com.example.clean_meals.viewmodel.LunchViewModel
import com.example.clean_meals.widget.DataUtil
import kotlinx.android.synthetic.main.breakfast_fragment.*
import kotlinx.android.synthetic.main.lunch_fragment.*

class LunchFragment : BaseFragment() {

    lateinit var binding: LunchFragmentBinding
    lateinit var viewModel: LunchViewModel

    override fun title(): String {
        return "점심"
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.lunch_fragment, container, false)
        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observerVieModel()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[LunchViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun observerVieModel() {
        with(viewModel) {
            DataUtil.meal.observe(viewLifecycleOwner, Observer {
                lunch.value = it.lunch
            })
        }
    }
}