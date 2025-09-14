package com.example.domain.usecases

import com.example.domain.models.HistoryItem
import com.example.domain.repository.LocalStorageRepository

class SetStarredUseCase(private val repository: LocalStorageRepository) {
    suspend operator fun invoke(query: HistoryItem, isStarred: Boolean) =
        repository.setStarred(query, isStarred)
}
