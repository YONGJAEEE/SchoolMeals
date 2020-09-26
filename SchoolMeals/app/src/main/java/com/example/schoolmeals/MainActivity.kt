package com.example.schoolmeals

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
            val intent = Intent(this,SearchScActivity::class.java)
            startActivity(intent)
        }else{
            SchoolName.setText(ScName)
            Schoolcode.setText(ascCode)
            Schoolcode2.setText(scCode)
        }


//        val dateToday = Timestamp(System.currentTimeMillis()).time
//
//        sample.setText("${dateToday}")
    }
}