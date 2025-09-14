package com.example.data.local.mappers

import com.example.data.local.entity.WordEntity
import com.example.domain.models.HistoryItem

fun WordEntity.toDomain() = HistoryItem(
    russian = russian,
    english = english,
    isStarred = isStarred
)

fun HistoryItem.toEntity() = WordEntity(
    russian = russian,
    english = english,
    isStarred = isStarred
)
