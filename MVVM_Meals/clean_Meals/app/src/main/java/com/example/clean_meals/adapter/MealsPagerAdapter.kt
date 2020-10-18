package com.example.clean_meals.adapter


import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class MealsPagerAdapter(var viewList : ArrayList<View>, var viewPager: ViewPager) : PagerAdapter() {
    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val temp = viewList[position]
        viewPager.addView(temp)
        return viewList[position]
    }
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        viewPager.removeView(obj as View)
    }
}
