package com.example.domain.usecases

import com.example.domain.repository.LocalStorageRepository

class PagedStarredUseCase(private val repository: LocalStorageRepository) {
    operator fun invoke(pageSize: Int = 10) = repository.pagedStarred(pageSize)
}
