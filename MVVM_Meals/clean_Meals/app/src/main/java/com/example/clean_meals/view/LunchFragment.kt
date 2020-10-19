package com.example.clean_meals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.clean_meals.R
import kotlinx.android.synthetic.main.lunch_fragment.*

class LunchFragment : BaseFragment() {
    override fun title(): String {
        return "점심"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lunch_fragment, container, false)
        view.findViewById<TextView>(R.id.tv_lunch).text = "점심점심"
        return view
    }
}