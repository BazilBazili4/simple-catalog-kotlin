package com.example.catalog.services

import com.example.catalog.models.City
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
                val city = it.createObject<City>(cityCount)
                city.title = "testName{$cityCount}"
                city.logoImg = "30"
            }

        }

    }

    fun getAllCities(): RealmResults<City> {
        val cities = realm.where<City>().findAll()
        return  cities
    }

    fun getAllCitiesAsArray(): ArrayList<String> {
        val cities = realm.where<City>().findAll()
        val citiesArray: ArrayList<String> = ArrayList<String>()
        cities.forEach {
            citiesArray.add(it.title)
        }
        return citiesArray
    }
}