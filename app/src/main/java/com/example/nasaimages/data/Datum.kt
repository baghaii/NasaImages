package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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