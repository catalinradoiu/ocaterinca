package com.ocaterinca.ocaterinca.data.di

import org.koin.dsl.module

object AppModules {

    private val apiModules = module {
//        single { retrofit.CreateServiceBLabla }
    }

    val modules =
        listOf(apiModules)
}