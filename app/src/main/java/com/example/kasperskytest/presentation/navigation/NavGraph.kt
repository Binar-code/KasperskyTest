package com.example.kasperskytest.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.difference
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kasperskytest.R
import com.example.kasperskytest.presentation.screens.main.Main
import com.example.kasperskytest.ui.theme.KasperskyTestTheme

private object Routes {
    const val MAIN = "main"
    const val STARRED = "starred"
}

@Composable
fun App() {
    val nav = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(84.dp)
            ) {
                NavigationBarItem(
                    selected = nav.currentDestination?.route == Routes.MAIN,
                    onClick = { nav.navigate(Routes.MAIN) { launchSingleTop = true } },
                    icon = { Icon(Icons.Default.Home, contentDescription = Routes.MAIN) },
                    label = { Text(stringResource(R.string.translator)) }
                )
                NavigationBarItem(
                    selected = nav.currentDestination?.route == Routes.STARRED,
                    onClick = { nav.navigate(Routes.STARRED) { launchSingleTop = true } },
                    icon = { Icon(Icons.Default.Star, contentDescription = Routes.STARRED) },
                    label = { Text(stringResource(R.string.starred)) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = nav,
            startDestination = Routes.MAIN,
            modifier = Modifier.padding(innerPadding).statusBarsPadding()
        ) {
            composable(Routes.MAIN) {
                Main()
            }

            composable(Routes.STARRED) {

            }
        }
    }
}
