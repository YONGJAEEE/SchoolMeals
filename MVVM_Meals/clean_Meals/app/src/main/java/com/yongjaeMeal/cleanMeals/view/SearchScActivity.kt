package com.yongjaeMeal.cleanMeals.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongjaeMeal.cleanMeals.R
import com.yongjaeMeal.cleanMeals.adapter.ScListAdapter
import com.yongjaeMeal.cleanMeals.databinding.ActivitySearchScBinding
import com.yongjaeMeal.cleanMeals.viewmodel.SearchScViewModel
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

        with(viewModel) {

            createAdapter.observe(this@SearchScActivity, Observer {
                val mAdapter = ScListAdapter(schoolList)
                Rv.adapter = mAdapter
            })

            statusValue.observe(this@SearchScActivity, Observer {
                when (statusValue.value) {
                    0 -> Toast.makeText(this@SearchScActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT)
                        .show()
                    200 -> {
                        Toast.makeText(this@SearchScActivity, "학교 검색에 성공했어요.", Toast.LENGTH_SHORT)
                            .show()
                        tv_nullSearch.setText("")
                    }
                    else -> {
                        Toast.makeText(this@SearchScActivity,
                            "검색에 실패했어요, 학교 이름을 확인한 후 다시 시도해주세요.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}