package com.yongjaeMeal.cleanMeals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongjaeMeal.cleanMeals.R
import com.yongjaeMeal.cleanMeals.databinding.BreakfastFragmentBinding
import com.yongjaeMeal.cleanMeals.viewmodel.BreakfastViewModel
import com.yongjaeMeal.cleanMeals.widget.DataUtil

class BreakfastFragment : BaseFragment() {

    lateinit var binding: BreakfastFragmentBinding
    lateinit var viewModel: BreakfastViewModel

    override fun title(): String {
        return "아침"
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.breakfast_fragment, container, false)
        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observerVieModel()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[BreakfastViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun observerVieModel() {
        with(viewModel) {
            DataUtil.meal.observe(viewLifecycleOwner, Observer {
                breakfast.value = it.breakfast
            })
        }
    }
}