package com.example.checkbin.di

import com.example.checkbin.presentation.screens.checkbin.CheckBinScreenViewModel
import com.example.checkbin.presentation.screens.history.HistoryBinDataScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // CheckBinScreenViewModel
    viewModel {
        CheckBinScreenViewModel(
            binDataRepository = get(),
            binDataHistoryRepository = get(),
            dispatcher = get()
        )
    }

    // HistoryBinDataScreenViewModel
    viewModel {
        HistoryBinDataScreenViewModel(
            binDataHistoryRepository = get(),
            dispatcher = get()
        )
    }
}