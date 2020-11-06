package com.yongjaeMeal.cleanMeals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yongjaeMeal.cleanMeals.view.BreakfastFragment
import com.yongjaeMeal.cleanMeals.view.DinnerFragment
import com.yongjaeMeal.cleanMeals.view.LunchFragment


class MealsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                val Breafast =
                    BreakfastFragment()
                return Breafast
            }
            1->{
                val Lunch =
                    LunchFragment()
                return Lunch
            }
            2->{
                val Dinner =
                    DinnerFragment()
                return Dinner
            }
            else->{
                return null!!
            }
        }
    }
    override fun getCount(): Int {
        return 3
    }
}
