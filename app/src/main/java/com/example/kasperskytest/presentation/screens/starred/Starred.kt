package com.example.kasperskytest.presentation.screens.starred

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kasperskytest.R
import com.example.kasperskytest.presentation.components.History
import org.koin.androidx.compose.koinViewModel

@Composable
fun Starred(
    vm: StarredViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()
    Box(
        Modifier.padding(horizontal = 16.dp)
    ) {
        if (uiState.starred != null) {
            val starred = uiState.starred!!.collectAsLazyPagingItems()
            History(
                history = starred,
                delete = { item -> vm.deleteItem(item) },
                star = { item -> vm.starItem(item) },
                label = stringResource(R.string.starred_label)
            )
        }
    }
}
