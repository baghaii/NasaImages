package com.example.nasaimages.common

import com.example.nasaimages.data.Datum

/*
    Since NASA sometimes provides accessibility descriptions, I wanted to use those. However,
    a lot of the accessibility descriptions were just the regular descriptions. But sometimes,
    there were no accessibility descriptions and very long descriptions. I decided the limit the
    length of descriptions as accessibility descriptions so as not to annoy people who use TalkBack
    by reading them an entire essay.
 */
val Datum.contentDescription
    get() = this.description508 ?:
        this.description?.substring(0, minOf(this.description.length, 250)) ?:
        "Untitled image"