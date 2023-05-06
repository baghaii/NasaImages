package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    @Expose
    val href: String
)