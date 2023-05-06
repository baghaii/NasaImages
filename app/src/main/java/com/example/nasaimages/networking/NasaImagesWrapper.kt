package com.example.nasaimages.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NasaImagesWrapper(
    @SerializedName("collection")
    @Expose
    val collection: MyCollection
)

data class MyCollection(
    @SerializedName("items")
    @Expose
    val items: List<Item>
)

data class Item(
    @SerializedName("href")
    @Expose
    val href: String,
    val data: List<Datum>,
    val links: List<Link>
)

data class Datum(
    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("photographer")
    @Expose
    val photographer: String,

    @SerializedName("location")
    @Expose
    val location: String,

    @SerializedName("date_created")
    @Expose
    val date_created: String
)

data class Link(
    @SerializedName("href")
    @Expose
    val href: String
)