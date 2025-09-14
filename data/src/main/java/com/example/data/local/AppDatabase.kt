package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.WordDao
import com.example.data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
