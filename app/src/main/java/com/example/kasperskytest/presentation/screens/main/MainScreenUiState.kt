package com.example.kasperskytest.presentation.screens.main

import androidx.paging.PagingData
import com.example.domain.models.HistoryItem
import kotlinx.coroutines.flow.Flow

sealed class MainScreenError {
    object NoInternet: MainScreenError()
    object Unexpected: MainScreenError()
    data class Http(val msg: String?): MainScreenError()
}

data class MainScreenUiState(
    val input: String = "",
    val translation: String = "",
    val history: Flow<PagingData<HistoryItem>>? = null,
    val error: MainScreenError? = null
)
