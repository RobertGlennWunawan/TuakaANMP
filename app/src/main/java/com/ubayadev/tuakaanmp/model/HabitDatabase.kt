package com.ubayadev.tuakaanmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.tuakaanmp.util.DB_NAME
import com.ubayadev.tuakaanmp.util.MIGRATION_1_2

@Database(entities = [User::class, Habit::class], version = 2)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: HabitDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: Room.databaseBuilder(
                context.applicationContext, HabitDatabase::class.java, DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        db.execSQL("INSERT INTO user (username, password) VALUES ('student', '123')")
                    }
                })
                .build().also { instance = it }
        }
    }
}