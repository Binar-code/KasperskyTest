package com.example.kasperskytest.presentation.screens.starred

import androidx.paging.PagingData
import com.example.domain.models.HistoryItem
import kotlinx.coroutines.flow.Flow

data class StarredUiState(
    val starred: Flow<PagingData<HistoryItem>>? = null,
)
