package com.example.clean_meals.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.clean_meals.widget.MyApplication
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {
    var SchoolName = MyApplication.prefs.getString("schoolName","null")
    var Date : LocalDate = LocalDate.now()
    init {

    }
}