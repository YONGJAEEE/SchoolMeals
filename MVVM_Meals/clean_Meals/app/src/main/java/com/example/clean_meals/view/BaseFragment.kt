package com.example.clean_meals.view

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun title(): String
}