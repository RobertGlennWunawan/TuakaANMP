package com.ubayadev.tuakaanmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ubayadev.tuakaanmp.model.Habit
import com.ubayadev.tuakaanmp.model.HabitDatabase
import com.ubayadev.tuakaanmp.util.buildDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLD = MutableLiveData<Habit>()

    fun fetchHabit(habitId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = HabitDatabase.buildDatabase(getApplication())
            val habit = db.habitDao().selectHabit(habitId)

            withContext(Dispatchers.Main) {
                habitLD.value = habit
            }
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = HabitDatabase.buildDatabase(getApplication())
            db.habitDao().updateHabit(habit)

            refresh()
        }
    }

    fun addHabit(title: String, desc: String, target: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = HabitDatabase.buildDatabase(getApplication())
            val newHabit = Habit(title, desc, target, 0)

            db.habitDao().insertAll(newHabit)

            val currentHabits = db.habitDao().selectAllHabits()
            val arrayListHabits = ArrayList(currentHabits)

            withContext(Dispatchers.Main) {
                habitsLD.value = arrayListHabits
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val db = HabitDatabase.buildDatabase(getApplication())
            val currentHabits = db.habitDao().selectAllHabits()
            val arrayListHabits = ArrayList(currentHabits)

            withContext(Dispatchers.Main) {
                habitsLD.value = arrayListHabits
            }
        }
    }

    fun updateProgress(habitId: Int, value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = HabitDatabase.buildDatabase(getApplication())
            val habit = db.habitDao().selectHabit(habitId)

            if (habit != null) {
                habit.currentProgress += value
                if (habit.currentProgress < 0) habit.currentProgress = 0
                if (habit.currentProgress > habit.target) habit.currentProgress = habit.target

                db.habitDao().updateHabit(habit)
            }
            refresh()
        }
    }
}