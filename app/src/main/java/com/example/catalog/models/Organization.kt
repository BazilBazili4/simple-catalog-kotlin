package com.example.catalog.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Organization(
    @PrimaryKey var id: Long = 1,
    var title: String = "title",
    var shortTitle: String = "short_title",
    var description: String = "description",
    var shortDescription: String = "short_description",
    var factsDescription: String = "facts_description",
    var address: String = "address",
    var phone: String = "phone",
    var email: String = "email",
    var logoImg: String = "https://klike.net/uploads/posts/2019-06/1561110628_2.jpg",
    var programmUrl: String = "programm_link",
    var siteUrl: String = "site_link",
    var city: String = "city",
    var direction: String = "direction",
    var vkLink: String = "vk_link",
    var classmatesLink: String = "classmates_link",
    var instagramLink: String = "instagram_link",
    var youtubeLink: String = "youtube_link",
    var longitude: Double  = 23.77,
    var latitude: Double  = 23.77,
    var isFavorite: Boolean = false
) : RealmObject()