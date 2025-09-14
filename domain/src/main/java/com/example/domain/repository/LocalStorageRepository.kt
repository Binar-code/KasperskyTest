package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.models.HistoryItem
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {
    suspend fun upsertItem(query: HistoryItem)

    suspend fun deleteItem(query: HistoryItem)

    suspend fun setStarred(query: HistoryItem, isStarred: Boolean)

    fun pagedHistory(pageSize: Int): Flow<PagingData<HistoryItem>>

    fun pagedStarred(pageSize: Int): Flow<PagingData<HistoryItem>>
}
