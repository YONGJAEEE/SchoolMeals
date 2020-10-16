package com.example.schoolmeals.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.example.schoolmeals.API.MealsAPI
import com.example.schoolmeals.MyApplication
import com.example.schoolmeals.R
import com.example.schoolmeals.Responses.MealsResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.breakfastpage.view.*
import kotlinx.android.synthetic.main.dinnerpage.view.*
import kotlinx.android.synthetic.main.lunchpage.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp
import java.time.LocalDate
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    var viewList = ArrayList<View>()

    val BASE_URL = "https://kyj-school-server.herokuapp.com/"

    var dateToday = (Timestamp(System.currentTimeMillis()).time / 1000L) * 1000

    var realDate: LocalDate = LocalDate.now()

    var ScName = MyApplication.prefs.getString("SchoolName", "null")
    var ascCode = MyApplication.prefs.getString("ascCode", "null")
    var scCode = MyApplication.prefs.getString("scCode", "null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewList.add(layoutInflater.inflate(R.layout.breakfastpage, null))
        viewList.add(layoutInflater.inflate(R.layout.lunchpage, null))
        viewList.add(layoutInflater.inflate(R.layout.dinnerpage, null))

        MealsPager.adapter = pagerAdapter()


        if (ScName == "null" && ascCode == "null" && scCode == "null") {
            val intent = Intent(this, SearchScActivity::class.java)
            startActivity(intent)
        }

        tv_ScName.setText(ScName)

        Log.d("time", dateToday.toString())
        tv_date.setText(realDate.toString())
        getMeals(dateToday)

        btn_before.setOnClickListener() {
            dateToday -= 86400000
            realDate = realDate.minusDays(1)
            tv_date.setText(realDate.toString())
            getMeals(dateToday)
        }
        btn_next.setOnClickListener() {
            dateToday += 86400000
            realDate = realDate.plusDays(1)
            tv_date.setText(realDate.toString())
            getMeals(dateToday)
        }

        btn_setting.setOnClickListener(){
            var intent = Intent(this, SearchScActivity::class.java)
            startActivity(intent)
        }
    }

    fun getMeals(date: Long) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MealsAPI::class.java)

        val call = service.CallMeals(ascCode, scCode, date.toString())

        call.enqueue(object : Callback<MealsResponse> {
            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {

                Log.d("Sucess", response.body().toString())

                if (response.body()?.breakfast?.lists != null) {
                    viewList[0].tv_breakfast.setText(
                        response.body()?.breakfast?.lists.toString().replace("<br/>", "\n\n")
                    )
                } else if (response.body()?.breakfast?.lists == null) {
                    viewList[0].tv_breakfast.setText("급식이 존재하지 않아요.")
                }
                if (response.body()?.lunch?.lists != null) {
                    viewList[1].tv_lunch.setText(
                        response.body()?.lunch?.lists.toString().replace("<br/>", "\n\n")
                    )
                } else if (response.body()?.lunch?.lists == null) {
                    viewList[1].tv_lunch.setText("급식이 존재하지 않아요.")
                }
                if (response.body()?.dinner?.lists != null) {
                    viewList[2].tv_dinner.setText(
                        response.body()?.dinner?.lists.toString().replace("<br/>", "\n\n")
                    )
                } else if (response.body()?.dinner?.lists == null) {
                    viewList[2].tv_dinner.setText("급식이 존재하지 않아요.")
                }
            }

            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                Log.d("fail", "레트로핏 2주 압수")
            }
        })
    }

    inner class pagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return viewList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var temp = viewList[position]
            MealsPager.addView(temp)
            return viewList[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            MealsPager.removeView(obj as View)
        }

    }
}


