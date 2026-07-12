package com.ubayadev.tuakaanmp.view

import android.view.View
import com.ubayadev.tuakaanmp.model.Habit

interface HabitLayoutInterface {
    fun onButtonPlusClick(v: View, obj: Habit)
    fun onButtonMinusClick(v: View, obj: Habit)
    fun onTitleClick(v: View)
}