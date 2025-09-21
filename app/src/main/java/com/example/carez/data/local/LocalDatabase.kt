package com.example.carez.data.local

import android.app.Application
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.example.carez.data.local.dao.UserDao
import com.example.carez.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Application) : LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, LocalDatabase::class.java, "room_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}