package com.example.clean_meals.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clean_meals.R
import com.example.clean_meals.adapter.MealsPagerAdapter
import com.example.clean_meals.databinding.ActivityMainBinding
import com.example.clean_meals.viewmodel.MainViewModel
import com.example.clean_meals.widget.MyApplication
import kotlinx.android.synthetic.main.activity_main.*

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel
    var schoolName = MyApplication.prefs.getString("schoolName","null")
    var officeCode = MyApplication.prefs.getString("officeCode","null")
    var schoolId = MyApplication.prefs.getString("schoolId","null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        if (schoolName=="null" && officeCode=="null" && schoolId=="null"){
            val intent = Intent(this,SearchScActivity::class.java)
            startActivity(intent)
        }

            val adapter = MealsPagerAdapter(supportFragmentManager)
            view_MealsPager.adapter = adapter
            view_MealsPager.currentItem = 1
            adapter.notifyDataSetChanged()

        with(viewModel){
            liveDate.observe(this@MainActivity, Observer {
                tv_date.setText(liveDate.value)
            })
            button.observe(this@MainActivity, Observer {
                Log.d("TAG","asd")
                val intent = Intent(this@MainActivity,SearchScActivity::class.java)
                startActivity(intent)
            })
        }
    }

}