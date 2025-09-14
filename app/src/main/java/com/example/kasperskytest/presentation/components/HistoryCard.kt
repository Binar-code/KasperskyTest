package com.example.kasperskytest.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.HistoryItem

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier,
    item: HistoryItem,
    delete: (HistoryItem) -> Unit,
    star: (HistoryItem) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 18.dp)
        ) {
            Column {
                Text(
                    text = item.english,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp
                )
                Text(
                    text = item.russian,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.clickable(
                    onClick = { star(item) }
                ),
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (item.isStarred) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier.clickable(
                    onClick = { delete(item) }
                ),
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}
