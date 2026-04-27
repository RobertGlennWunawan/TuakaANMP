package com.ubayadev.tuakaanmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.tuakaanmp.model.Habit

class HabitViewModel : ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()

    init {
        val listHabit = arrayListOf(
            Habit(1, "Minum Air", "8 gelas sehari", 8, 3),
            Habit(2, "Jalan Kaki", "10000 langkah", 10000, 2500),
            Habit(3, "Belajar", "1 jam sehari", 60, 20)
        )
        habitsLD.value = listHabit
    }

    fun refresh() {
        habitsLD.value = habitsLD.value
    }

    fun addHabit(name: String, desc: String, target: Int) {
        val list = habitsLD.value ?: arrayListOf()

        val newId = if (list.isEmpty()) 1 else list.last().id + 1
        val habit = Habit(newId, name, desc, target, 0)

        list.add(habit)
        habitsLD.value = list
    }

    fun updateProgress(habitId: Int, value: Int) {
        val list = habitsLD.value
        val habit = list?.find { it.id == habitId }

        habit?.let {
            it.currentProgress += value

            if (it.currentProgress < 0) it.currentProgress = 0
            if (it.currentProgress > it.target) it.currentProgress = it.target

            habitsLD.value = list
        }
    }
}