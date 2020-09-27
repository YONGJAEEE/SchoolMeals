package com.example.schoolmeals.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.schoolmeals.MyApplication
import com.example.schoolmeals.R
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ScName = MyApplication.prefs.getString("SchoolName", "null")
        var ascCode = MyApplication.prefs.getString("ascCode", "null")
        var scCode = MyApplication.prefs.getString("scCode", "null")


        if (ScName == "null" && ascCode == "null" && scCode == "null") {
            val intent = Intent(this, SearchScActivity::class.java)
            startActivity(intent)
        }

            tv_ScName.setText(ScName)




        val dateToday = Timestamp(System.currentTimeMillis()).time*1000
        tv_date.setText(dateToday.toString())
    }
}
