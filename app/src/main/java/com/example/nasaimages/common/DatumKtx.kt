package com.example.nasaimages.common

import com.example.nasaimages.data.Datum

val Datum.contentDescription
    get() = this.description508 ?: this.description ?: "Untitled image"