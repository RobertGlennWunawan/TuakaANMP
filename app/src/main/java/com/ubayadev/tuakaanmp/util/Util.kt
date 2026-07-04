package com.ubayadev.tuakaanmp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.tuakaanmp.model.HabitDatabase

val DB_NAME = "habitdb"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE habit ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

fun buildDb(context: Context): HabitDatabase {
    return HabitDatabase.buildDatabase(context)
}