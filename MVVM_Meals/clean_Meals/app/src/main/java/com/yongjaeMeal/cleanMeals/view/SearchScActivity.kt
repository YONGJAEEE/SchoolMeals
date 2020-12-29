package com.yongjaeMeal.cleanMeals.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongjaeMeal.cleanMeals.R
import com.yongjaeMeal.cleanMeals.adapter.ScListAdapter
import com.yongjaeMeal.cleanMeals.databinding.ActivitySearchScBinding
import com.yongjaeMeal.cleanMeals.viewmodel.SearchScViewModel
import com.yongjaeMeal.cleanMeals.widget.TextObserver
import kotlinx.android.synthetic.main.activity_search_sc.*

class SearchScActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchScBinding
    lateinit var viewModel: SearchScViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_sc)
        viewModel = ViewModelProvider(this)[SearchScViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        et_search.addTextChangedListener(TextObserver(viewModel))

        with(viewModel) {
            createAdapter.observe(this@SearchScActivity, Observer {
                if (schoolList.size == 0) tv_nullSearch.text = "검색 결과가 없습니다."
                else tv_nullSearch.text = ""
                val mAdapter = ScListAdapter(schoolList)
                Rv.adapter = mAdapter
            })
        }
    }
}