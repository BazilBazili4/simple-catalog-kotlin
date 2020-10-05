package com.example.catalog.services

import com.example.catalog.models.City
import com.example.catalog.models.Direction
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where


class DatabaseService {

    val realm : Realm = Realm.getDefaultInstance()

    fun createDummyData() {

        //The writing to our database always must be in a transaction
        realm.executeTransaction {

            for(cityCount in 0 until 10){
                //The value we send as parameter is the primary key
                val city = it.createObject<City>(cityCount)
                city.title = "testName{$cityCount}"
                city.logoImg = "30"
            }

        }

    }

    fun createDummyDirection() {

        //The writing to our database always must be in a transaction
        realm.executeTransaction {

            for(cityCount in 0 until 10){
                //The value we send as parameter is the primary key
                val direction = it.createObject<Direction>(cityCount)
                direction.title = "testName{$cityCount}"
                direction.description = "testDescription{$cityCount}"
            }

        }

    }

    fun getAllCities(): RealmResults<City> {
        val cities = realm.where<City>().findAll()
        return  cities
    }

    fun getAllCitiesTitlesAsArray(): ArrayList<String> {
        val cities = getAllCities()
        val citiesArray: ArrayList<String> = ArrayList<String>()
        cities.forEach {
            citiesArray.add(it.title)
        }
        return citiesArray
    }

    fun getAllDirections(): RealmResults<Direction> {
        val direction = realm.where<Direction>().findAll()
        return  direction
    }

    fun getAllDirectionsTitlesAsArray(): ArrayList<String> {
        val directions = getAllDirections()
        val directionsArray: ArrayList<String> = ArrayList<String>()
        directions.forEach {
            directionsArray.add(it.title)
        }
        return directionsArray
    }
}