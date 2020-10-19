package com.example.clean_meals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.clean_meals.view.BaseFragment
import com.example.clean_meals.view.BreakfastFragment
import com.example.clean_meals.view.DinnerFragment
import com.example.clean_meals.view.LunchFragment


class MealsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val list: ArrayList<BaseFragment> = ArrayList();

    init {
        list.add(BreakfastFragment())
        list.add(LunchFragment())
        list.add(DinnerFragment())
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title()
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }
}
