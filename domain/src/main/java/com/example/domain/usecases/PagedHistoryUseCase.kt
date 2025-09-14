package com.example.domain.usecases

import com.example.domain.repository.LocalStorageRepository

class PagedHistoryUseCase(private val repository: LocalStorageRepository) {
    operator fun invoke(pageSize: Int = 10) = repository.pagedHistory(pageSize)
}