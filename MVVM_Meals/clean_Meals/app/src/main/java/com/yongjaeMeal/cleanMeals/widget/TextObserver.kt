package com.yongjaeMeal.cleanMeals.widget

import android.text.Editable
import android.text.TextWatcher
import com.yongjaeMeal.cleanMeals.viewmodel.SearchScViewModel

class TextObserver (val viewModel: SearchScViewModel) : TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        viewModel.getSchool()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}