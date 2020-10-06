package com.example.catalog.services

import com.example.catalog.models.City
import com.example.catalog.models.Direction
import com.example.catalog.models.Organization
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.net.Inet4Address


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

    fun createDummyOrganization() {

        //The writing to our database always must be in a transaction
        realm.executeTransaction {

            for(cityCount in 0 until 10){
                //The value we send as parameter is the primary key
                val organization = it.createObject<Organization>(cityCount)
                organization.title = "testName{$cityCount}"
                organization.description = "testDescription{$cityCount}"
                organization.shortTitle = "testShortTitle{$cityCount}"
                organization.shortDescription = "testShortDescription{$cityCount}"
                organization.address = "testAddress{$cityCount}"
                organization.phone = "testPhone{$cityCount}"
                organization.city = "testName{$cityCount}"
                organization.direction = "testName{$cityCount}"
                organization.factsDescription =  "testFactsDescription{$cityCount}"
                organization.siteUrl = "https://github.com"
                organization.programmUrl = "https://www.google.com"
                organization.vkLink = "https://www.vk.com"
                organization.instagramLink = "https://www.instagram.com"
                organization.classmatesLink = "https://ok.ru"
                organization.youtubeLink = "https://www.youtube.com"
            }

        }

    }

    fun getAllCities(): RealmResults<City> {
        val cities = realm.where<City>().findAllAsync()
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
        val direction = realm.where<Direction>().findAllAsync()
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

    fun getAllOrganizations(): RealmResults<Organization> {
        val organization = realm.where<Organization>().findAllAsync()
        return  organization
    }

    fun getOrganizationById(id: Long?): Organization? {
        return realm.where<Organization>()
            .equalTo("id", id)
            .findFirst()
    }

    fun getOrganizationsByParams(city: String?, direction: String?, phone: String?, address: String?, title: String?): RealmResults<Organization> {
        var query = realm.where<Organization>()
        if (!isNullOrEmpty(city)) query.equalTo("city", city).and()
        if (!isNullOrEmpty(direction)) query.equalTo("direction", direction).and()
        if (!isNullOrEmpty(phone)) query.equalTo("phone", phone).and()
        if (!isNullOrEmpty(address)) query.like("address", address).and()
        if (!isNullOrEmpty(title)) query.like("title", title)
        return query.findAllAsync()
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }

    fun getFavoritesOrganizations(): RealmResults<Organization> {
        return realm.where<Organization>()
            .equalTo("isFavorite", true)
            .findAll()
    }

    fun setOrganizationFavorite(id: Long) {
        val organization = getOrganizationById(id)
        realm.executeTransaction{
            organization?.isFavorite = true
        }
    }

    fun removeOrganizationFavorite(id: Long) {
        val organization = getOrganizationById(id)
        realm.executeTransaction{
            organization?.isFavorite = false
        }
    }
}