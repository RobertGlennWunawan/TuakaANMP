package com.ubayadev.tuakaanmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "target") var target: Int,
    @ColumnInfo(name = "current_progress") var currentProgress: Int,
    @ColumnInfo(name = "priority") var priority: Int = 3,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)