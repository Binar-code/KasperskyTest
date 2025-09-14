package com.example.kasperskytest.presentation.screens.starred

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.models.HistoryItem
import com.example.domain.usecases.DeleteItemUseCase
import com.example.domain.usecases.PagedStarredUseCase
import com.example.domain.usecases.SetStarredUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StarredViewModel(
    private val getStarred: PagedStarredUseCase,
    private val delete: DeleteItemUseCase,
    private val star: SetStarredUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(StarredUiState())
    val uiState: StateFlow<StarredUiState> = _uiState

    fun starItem(item: HistoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            star(item, !item.isStarred)
        }
    }

    fun deleteItem(item: HistoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            delete(item)
        }
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(starred = getStarred().cachedIn(viewModelScope)) }
        }
    }

    init {
        fetchData()
    }
}
