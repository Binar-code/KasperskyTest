package com.example.domain.usecases

import com.example.domain.models.HistoryItem
import com.example.domain.repository.LocalStorageRepository

class InsertItemUseCase(private val repository: LocalStorageRepository) {
    suspend operator fun invoke(query: HistoryItem) = repository.insertItem(query)
}
