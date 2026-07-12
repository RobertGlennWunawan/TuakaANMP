package com.ubayadev.tuakaanmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubayadev.tuakaanmp.model.Habit
import com.ubayadev.tuakaanmp.util.buildDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    fun refresh() {
        viewModelScope.launch {
            val db = buildDb(getApplication())
            val currentHabits = withContext(Dispatchers.IO) {
                db.habitDao().selectAllHabits()
            }

            val arrayListHabits = ArrayList(currentHabits)
            habitsLD.value = arrayListHabits
        }
    }

    fun addHabit(title: String, desc: String, target: Int) {
        viewModelScope.launch {
            val db = buildDb(getApplication())
            val newHabit = Habit(title, desc, target,0)

            // Simpan ke Room Database secara asinkronus
            withContext(Dispatchers.IO) {
                db.habitDao().insertAll(newHabit)
            }

            // Refresh tampilan setelah berhasil menambah data
            refresh()
        }
    }

    fun updateProgress(habitId: Int, value: Int) {
        viewModelScope.launch {
            val db = buildDb(getApplication())

            withContext(Dispatchers.IO) {
                val habit = db.habitDao().selectHabit(habitId)
                if (habit != null) {
                    habit.currentProgress += value
                    if (habit.currentProgress < 0) habit.currentProgress = 0
                    if (habit.currentProgress > habit.target) habit.currentProgress = habit.target

                    db.habitDao().updateHabit(habit)
                }
            }
            refresh()
        }
    }
}