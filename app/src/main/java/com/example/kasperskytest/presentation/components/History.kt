package com.example.kasperskytest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.domain.models.HistoryItem
import com.example.kasperskytest.R

@Composable
fun History(
    modifier: Modifier = Modifier,
    history: LazyPagingItems<HistoryItem>,
    delete: (HistoryItem) -> Unit,
    star: (HistoryItem) -> Unit,
    label: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
        if (history.itemCount != 0) {
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

                item {
                    Spacer(Modifier.size(12.dp))
                }
            }
        } else {
            Text(
                text = stringResource(R.string.empty),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}