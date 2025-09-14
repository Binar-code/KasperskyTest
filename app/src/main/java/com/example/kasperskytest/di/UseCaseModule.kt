package com.example.kasperskytest.di

import com.example.domain.usecases.DeleteItemUseCase
import com.example.domain.usecases.PagedHistoryUseCase
import com.example.domain.usecases.PagedStarredUseCase
import com.example.domain.usecases.SetStarredUseCase
import com.example.domain.usecases.TranslateUseCase
import com.example.domain.usecases.InsertItemUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<DeleteItemUseCase> {
        DeleteItemUseCase(get())
    }

    factory<PagedHistoryUseCase> {
        PagedHistoryUseCase(get())
    }

    factory<PagedStarredUseCase> {
        PagedStarredUseCase(get())
    }

    factory<InsertItemUseCase> {
        InsertItemUseCase(get())
    }

    factory<TranslateUseCase> {
        TranslateUseCase(get())
    }

    factory<SetStarredUseCase> {
        SetStarredUseCase(get())
    }
}
