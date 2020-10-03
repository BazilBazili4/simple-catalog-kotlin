package com.example.catalog.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catalog.models.city.City
import com.example.catalog.models.city.CityDao

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [City::class], version = 1, exportSchema = false)
public abstract class DatabaseService : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getDatabase(context: Context): DatabaseService {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "catalog_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}