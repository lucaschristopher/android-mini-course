package com.example.nybooks.data

import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTService {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "BMY4y3OOfD1fJftGQl41vAeahae62e4b",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}
