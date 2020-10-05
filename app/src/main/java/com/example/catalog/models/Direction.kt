package com.example.catalog.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Direction(
    @PrimaryKey var id: Long = 1,
    var title: String = "title",
    var description: String = "description",
) : RealmObject()