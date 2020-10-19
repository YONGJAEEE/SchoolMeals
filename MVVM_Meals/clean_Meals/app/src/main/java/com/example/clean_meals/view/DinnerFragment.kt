package com.example.clean_meals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.clean_meals.R
import kotlinx.android.synthetic.main.dinner_fragment.*

class DinnerFragment : BaseFragment() {
    override fun title(): String {
        return "저녁"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dinner_fragment, container, false)
        view.findViewById<TextView>(R.id.tv_dinner).text = "ㅅㅂ 뭐"
        return view
    }
}