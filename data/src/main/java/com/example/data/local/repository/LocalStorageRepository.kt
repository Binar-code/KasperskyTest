package com.example.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.dao.WordDao
import com.example.data.local.mappers.toDomain
import com.example.data.local.mappers.toEntity
import com.example.domain.models.HistoryItem
import com.example.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalStorageRepositoryImpl(
    private val dao: WordDao
) : LocalStorageRepository {
    override suspend fun upsertItem(query: HistoryItem) {
        dao.insert(query.toEntity())
    }

    override suspend fun deleteItem(query: HistoryItem) {
        dao.deleteByEnglish(query.english)
    }

    override suspend fun setStarred(
        query: HistoryItem,
        isStarred: Boolean
    ) {
        dao.setStarred(query.english, isStarred)
    }

    override fun pagedHistory(pageSize: Int): Flow<PagingData<HistoryItem>> =
        Pager(PagingConfig(pageSize = pageSize, prefetchDistance = 2)) { dao.pagingAll() }
            .flow
            .map { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }

    override fun pagedStarred(pageSize: Int): Flow<PagingData<HistoryItem>> =
        Pager(PagingConfig(pageSize = pageSize, prefetchDistance = 2)) { dao.pagingStarred() }
            .flow
            .map { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }
}
