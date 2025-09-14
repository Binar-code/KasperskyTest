package com.example.kasperskytest.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.models.HistoryItem
import com.example.kasperskytest.R
import com.example.kasperskytest.presentation.screens.main.MainScreenUiState

@Composable
fun History(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState,
    delete: (HistoryItem) -> Unit,
    star: (HistoryItem) -> Unit
) {
    val history = uiState.history!!.collectAsLazyPagingItems()

    if (history.itemCount != 0) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.history),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = history.itemCount
                ) { i ->
                    HistoryCard(
                        item = history[i]!!,
                        delete = delete,
                        star = star
                    )
                }
            }
        }
    }
}