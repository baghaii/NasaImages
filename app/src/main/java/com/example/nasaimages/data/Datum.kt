package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("description_508")
    @Expose
    val description508: String?,

    @SerializedName("nasa_id")
    @Expose
    val nasaId: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("photographer")
    @Expose
    val photographer: String?,

    @SerializedName("location")
    @Expose
    val location: String?,

    @SerializedName("date_created")
    @Expose
    val dateCreated: String
)