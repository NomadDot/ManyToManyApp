package com.drowsynomad.manytomanyapp.di

import com.drowsynomad.presentation.screens.details.DetailsViewModel
import com.drowsynomad.presentation.screens.main.MainViewModel
import org.koin.dsl.module

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

internal val presentationModule = module {
    factory { MainViewModel(get()) }
    factory { DetailsViewModel(get()) }
}