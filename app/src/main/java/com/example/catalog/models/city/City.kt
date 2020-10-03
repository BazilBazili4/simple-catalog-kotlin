package com.example.catalog.models.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
class City(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "short_title") val shortTitle: String,
    @ColumnInfo(name = "image_src") val imageSrc: String
)