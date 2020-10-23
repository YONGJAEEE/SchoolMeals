package com.example.clean_meals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clean_meals.R
import com.example.clean_meals.databinding.BreakfastFragmentBinding
import com.example.clean_meals.databinding.DinnerFragmentBinding
import com.example.clean_meals.viewmodel.BreakfastViewModel
import com.example.clean_meals.viewmodel.DinnerViewModel
import com.example.clean_meals.viewmodel.LunchViewModel
import com.example.clean_meals.widget.DataUtil
import kotlinx.android.synthetic.main.dinner_fragment.*

class DinnerFragment : BaseFragment() {

    lateinit var binding: DinnerFragmentBinding
    lateinit var viewModel: DinnerViewModel

    override fun title(): String {
        return "저녁"
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dinner_fragment, container, false)
        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observerVieModel()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[DinnerViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun observerVieModel() {
        with(viewModel) {
            DataUtil.meal.observe(viewLifecycleOwner, Observer {
                dinner.value = it.dinner
            })
        }
    }
}