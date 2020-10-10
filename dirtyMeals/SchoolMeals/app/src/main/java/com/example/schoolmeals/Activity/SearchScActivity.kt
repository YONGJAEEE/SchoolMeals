package com.example.schoolmeals.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolmeals.MyApplication
import com.example.schoolmeals.R
import com.example.schoolmeals.Responses.ScResponse
import com.example.schoolmeals.API.ScListAPI
import com.example.schoolmeals.Adapter.ScListAdapter
import kotlinx.android.synthetic.main.activity_search_sc.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchScActivity : AppCompatActivity() {

    val BASE_URL = "https://kyj-school-server.herokuapp.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_sc)


        searchbutton.setOnClickListener() {
            if (et_search.text.toString() == "") {
                Toast.makeText(this, "학교명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                var view = this.currentFocus
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                getScAPI()
            }
        }


    }

    fun getScAPI() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ScListAPI::class.java)

        val call = service.SearchSc(et_search.text.toString())

        call.enqueue(object : Callback<ScResponse> {

            override fun onResponse(call: Call<ScResponse>, response: Response<ScResponse>) {

                Log.d("Sucess", response.body().toString())
                val data = response.body()
                if (data != null) {
                    if (data.status == 200) {
                        Toast.makeText(this@SearchScActivity, data.message, Toast.LENGTH_SHORT)
                            .show()
                        val adapter = ScListAdapter(data)
                        Rv.adapter = adapter
                        Rv.layoutManager = LinearLayoutManager(
                            this@SearchScActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        Rv.setHasFixedSize(true)
                        nullSearch.setText("")
                        adapter.setItemClickListener(object : ScListAdapter.ItemClickListener {
                            override fun onClick(view: View, position: Int) {

                                val builder = AlertDialog.Builder(
                                    ContextThemeWrapper(
                                        this@SearchScActivity,
                                        R.style.Theme_AppCompat_Light_Dialog
                                    )
                                )
                                builder.setTitle("학교 선택")
                                builder.setMessage("${data.data?.sc_list?.get(0)?.school_name}으로 선택하시겠습니까?")

                                builder.setPositiveButton("확인") { dialog, id ->
                                    data.data?.sc_list?.get(0)?.school_name?.let {
                                        MyApplication.prefs.setString("SchoolName", it)
                                    }
                                    data.data?.sc_list?.get(0)?.a_sc_code?.let {
                                        MyApplication.prefs.setString("ascCode", it)
                                    }
                                    data.data?.sc_list?.get(0)?.sc_code?.let {
                                        MyApplication.prefs.setString("scCode", it)
                                    }
                                    val intent =
                                        Intent(this@SearchScActivity, MainActivity::class.java)
                                    startActivity(intent)
                                }
                                builder.setNegativeButton("취소") { dialog, id ->
                                    Toast.makeText(this@SearchScActivity, "학교 선택을 취소했어요.", Toast.LENGTH_SHORT).show()
                                }

                                builder.show()


                            }
                        })

                    }
                } else {
                    nullSearch.setText("검색 결과가 없습니다.")
                    Toast.makeText(this@SearchScActivity, "학교를 찾을수 없어요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ScResponse>, t: Throwable) {
                Log.d("fail", t.toString())
                println("받아오기 실패")
            }
        })
    }

}