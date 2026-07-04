package com.ubayadev.tuakaanmp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit: Habit)
    @Query("SELECT * FROM habit ORDER BY priority DESC")
    fun selectAllHabits(): List<Habit>
    @Query("SELECT * FROM habit WHERE id = :habitId")
    fun selectHabit(habitId: Int): Habit
    @Update
    fun updateHabit(habit: Habit)
}