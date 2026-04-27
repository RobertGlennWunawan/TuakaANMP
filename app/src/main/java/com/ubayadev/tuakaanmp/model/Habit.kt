package com.ubayadev.tuakaanmp.model

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val target: Int,
    var currentProgress: Int = 0,
    val iconUrl: String = ""
)