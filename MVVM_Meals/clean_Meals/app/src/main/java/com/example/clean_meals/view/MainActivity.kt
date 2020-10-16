package com.example.clean_meals.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.clean_meals.R
import com.example.clean_meals.databinding.ActivityMainBinding
import com.example.clean_meals.viewmodel.MainViewModel
import com.example.clean_meals.widget.MyApplication

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

        if (schoolName=="null" && officeCode=="null" && schoolId=="null"){
            val intent = Intent(this,SearchScActivity::class.java)
            startActivity(intent)
        }
    }
}