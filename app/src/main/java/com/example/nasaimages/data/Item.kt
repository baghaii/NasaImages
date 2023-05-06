package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("href")
    @Expose
    val href: String,
    val data: List<Datum>,
    val links: List<Link>
)