package com.ocaterinca.ocaterinca.data.di

import com.ocaterinca.ocaterinca.feature.AvatarCardInteractor
import com.ocaterinca.ocaterinca.feature.AvatarCardViewModel
import com.ocaterinca.ocaterinca.feature.AvatarService
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeInteractor
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeService
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModules {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://e3tepsac4l.execute-api.eu-west-1.amazonaws.com/dev/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    private val apiModules = module {
        single { retrofit.create(AvatarService::class.java) }
        single { retrofit.create(GameCodeService::class.java) }
    }

    private val interactorModule = module {
        single { AvatarCardInteractor(get()) }
        single { GameCodeInteractor(get()) }
    }

    private val viewModelModule = module {
        viewModel { AvatarCardViewModel(get()) }
        viewModel { GameCodeViewModel(get()) }
    }

    val modules =
        listOf(apiModules, interactorModule, viewModelModule)
}