package com.example.kasperskytest.presentation.screens.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kasperskytest.R
import com.example.kasperskytest.presentation.screens.main.components.Header
import com.example.kasperskytest.presentation.components.History
import com.example.kasperskytest.presentation.screens.main.components.Input
import org.koin.androidx.compose.koinViewModel

@Composable
fun Main(
    vm: MainScreenViewModel = koinViewModel(),
) {
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.error) {
        val msg: String? = when (uiState.error) {
            is MainScreenError.Http -> context.getString(R.string.http_error)
            MainScreenError.NoInternet -> context.getString(R.string.network_error)
            MainScreenError.Unexpected -> context.getString(R.string.unknown_error)
            else -> null
        }
        if (msg != null) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            vm.clearError()
        }
    }

    Column(
        Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Header()

                HorizontalDivider(thickness = 1.dp)

                Input(
                    uiState = uiState,
                    onValueChange = { query -> vm.handleInputChange(query) }
                )

                if (uiState.input.isNotBlank()) {
                    Box(
                        Modifier.background(MaterialTheme.colorScheme.secondary).fillMaxWidth()
                    ) {
                        if (uiState.translation.isNotBlank()) {
                            Text(
                                text = uiState.translation,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 18.dp)
                            )
                        } else {
                            CircularProgressIndicator(
                                trackColor = MaterialTheme.colorScheme.primary,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .padding(horizontal = 18.dp, vertical = 18.dp)
                                    .size(26.dp),
                                strokeWidth = 3.dp
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        History(
            history = uiState.history!!.collectAsLazyPagingItems(),
            delete = { item -> vm.deleteItem(item) },
            star = { item -> vm.starItem(item) },
            label = stringResource(R.string.history)
        )
    }
}
