package com.ubayadev.tuakaanmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.tuakaanmp.model.Habit

class HabitViewModel : ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()

    fun refresh() {
        val data = arrayListOf(
            Habit(1, "Minum Air", "8 gelas sehari", 8, 3)
        )
        habitsLD.value = data
    }

    fun updateProgress(habitId: Int, value: Int) {
        val currentList = habitsLD.value
        val habit = currentList?.find { it.id == habitId }

        habit?.let {
            it.currentProgress += value
            if (it.currentProgress < 0) it.currentProgress = 0
            habitsLD.value = currentList
        }
    }
}