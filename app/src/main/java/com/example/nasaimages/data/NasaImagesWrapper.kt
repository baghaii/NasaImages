package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NasaImagesWrapper(
    @SerializedName("collection")
    @Expose
    val collection: MyCollection
)

