package com.example.domain.repository

import com.example.domain.models.Translation
import com.example.domain.response.Result

interface NetworkRepository {
    suspend fun translate(query: String): Result<Translation>
}
