package com.example.data.network.api

import com.example.data.network.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("api/public/v1/words/search")
    suspend fun search(
        @Query("search") query: String,
    ): List<WordDto>
}