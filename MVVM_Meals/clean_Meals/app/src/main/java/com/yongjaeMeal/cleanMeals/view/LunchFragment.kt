package com.yongjaeMeal.cleanMeals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongjaeMeal.cleanMeals.R
import com.yongjaeMeal.cleanMeals.databinding.LunchFragmentBinding
import com.yongjaeMeal.cleanMeals.viewmodel.LunchViewModel
import com.yongjaeMeal.cleanMeals.widget.DataUtil

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