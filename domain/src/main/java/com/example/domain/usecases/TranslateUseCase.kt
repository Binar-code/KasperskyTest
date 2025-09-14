package com.example.domain.usecases

import com.example.domain.models.Translation
import com.example.domain.repository.NetworkRepository
import com.example.domain.response.Result

class TranslateUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(query: String): Result<Translation> = repository.translate(query)
}