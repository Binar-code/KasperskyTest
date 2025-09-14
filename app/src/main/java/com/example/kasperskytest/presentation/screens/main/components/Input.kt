package com.example.kasperskytest.presentation.screens.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.kasperskytest.R
import com.example.kasperskytest.presentation.screens.main.MainScreenUiState

@Composable
fun Input(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    uiState: MainScreenUiState
) {
    TextField(
        value = uiState.input,
        onValueChange = { new ->
            val oneWord = new
                .replace('\n', ' ')
                .trimStart()
                .split(Regex("\\s+"), limit = 2)
                .first()
            onValueChange(oneWord)
        },
        placeholder = { Text(
            stringResource(R.string.input_placeholder),
            fontSize = 24.sp,
        ) },
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onPrimary
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}
