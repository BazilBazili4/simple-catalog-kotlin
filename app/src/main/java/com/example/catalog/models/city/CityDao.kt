package com.example.catalog.models.city

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * from city ORDER BY title ASC")
    fun getAlphabetizedCities(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: City)

    @Query("DELETE FROM city")
    suspend fun deleteAll()
}