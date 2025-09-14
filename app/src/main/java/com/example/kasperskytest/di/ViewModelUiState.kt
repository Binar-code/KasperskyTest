package com.example.kasperskytest.di

import com.example.kasperskytest.presentation.screens.main.MainScreenViewModel
import com.example.kasperskytest.presentation.screens.starred.StarredViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel<MainScreenViewModel> {
        MainScreenViewModel(get(), get(), get(), get(), get())
    }

    viewModel<StarredViewModel> {
        StarredViewModel(get(), get(), get())
    }
}
