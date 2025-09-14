package com.example.kasperskytest.presentation.screens.main

import androidx.paging.PagingData
import com.example.domain.models.HistoryItem
import kotlinx.coroutines.flow.Flow

sealed class UiError {
    object NoInternet: UiError()
    object Unexpected: UiError()
    data class Http(val msg: String?): UiError()
}

data class MainScreenUiState(
    val input: String = "",
    val translation: String = "",
    val history: Flow<PagingData<HistoryItem>>? = null,
    val error: UiError? = null
)
