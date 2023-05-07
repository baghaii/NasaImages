package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MyCollection(
    @SerializedName("href")
    @Expose
    val href: String,

    @SerializedName("items")
    @Expose
    val items: List<Item>
)