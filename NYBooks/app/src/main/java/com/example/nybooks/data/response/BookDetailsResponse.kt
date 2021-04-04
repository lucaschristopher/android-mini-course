package com.example.nybooks.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailsResponse(
    // Json Moshi's annotations was omitted because the parameter name is the same as the response
    val title: String,
    val description: String,
    val author: String
)