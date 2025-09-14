package com.example.kasperskytest.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.models.HistoryItem
import com.example.domain.models.Translation
import com.example.domain.response.NetworkError
import com.example.domain.response.Result
import com.example.domain.usecases.DeleteItemUseCase
import com.example.domain.usecases.PagedHistoryUseCase
import com.example.domain.usecases.TranslateUseCase
import com.example.domain.usecases.InsertItemUseCase
import com.example.domain.usecases.SetStarredUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val insert: InsertItemUseCase,
    private val delete: DeleteItemUseCase,
    private val history: PagedHistoryUseCase,
    private val star: SetStarredUseCase,
    private val translate: TranslateUseCase
):  ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<MainScreenEffects>()
    val uiEffect: SharedFlow<MainScreenEffects> = _uiEffect

    var translateJob: Job? = null

    fun translateInput(query: String) {
        translateJob?.cancel()
        translateJob = viewModelScope.launch(Dispatchers.IO) {
            if (uiState.value.input.isEmpty()) return@launch
            delay(600)
            try {
                val res = translate(query)
                when (res) {
                    is Result.Error -> {
                        when (res.error) {
                            is NetworkError.Http -> {
                                _uiState.update { it.copy(error = UiError.Http((res.error as NetworkError.Http).body)) }
                            }
                            is NetworkError.Unexpected -> {
                                _uiState.update { it.copy(error = UiError.Unexpected) }
                            }
                            NetworkError.NoConnection -> {
                                _uiState.update { it.copy(error = UiError.NoInternet) }
                            }
                        }
                    }
                    is Result.Ok<Translation> -> {
                        _uiState.update { it.copy(translation = res.value.text) }
                        insert(
                            HistoryItem(
                                english = normalize(_uiState.value.input),
                                russian = _uiState.value.translation,
                                isStarred = false
                            )
                        )
                    }
                }
            } catch (e: CancellationException) {
                throw e
            }
        }
    }

    fun handleInputChange(query: String) {
        _uiState.update { it.copy(input = query) }
        translateInput(query)
    }

    fun fetchHistory() {
        _uiState.update { it.copy(history = history().cachedIn(viewModelScope)) }
    }

    fun deleteItem(item: HistoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            delete(item)
        }
    }

    fun starItem(item: HistoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            star(item, !item.isStarred)
        }
    }

    private fun normalize(query: String): String = query.trim().lowercase()

    init {
        fetchHistory()
    }
}
