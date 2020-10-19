package com.example.clean_meals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.clean_meals.R

class BreakfastFragment : BaseFragment() {
    override fun title(): String {
        return "아침"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.breakfast_fragment, container, false)
        view.findViewById<TextView>(R.id.tv_breakfast).text = "아 ㅄ"
        return view
    }
}