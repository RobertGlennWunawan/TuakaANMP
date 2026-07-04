package com.ubayadev.tuakaanmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubayadev.tuakaanmp.util.DB_NAME
import com.ubayadev.tuakaanmp.util.MIGRATION_1_2

@Database(entities = [User::class, Habit::class], version = 2)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2).build().also { instance = it }
        }
    }

}