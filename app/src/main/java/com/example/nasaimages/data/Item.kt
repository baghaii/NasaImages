package com.example.nasaimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    val data: List<Datum>,
    val links: List<Link>
)