package com.ocaterinca.ocaterinca.data.di

import com.ocaterinca.ocaterinca.BuildConfig
import com.ocaterinca.ocaterinca.GameViewModel
import com.ocaterinca.ocaterinca.feature.AvatarCardInteractor
import com.ocaterinca.ocaterinca.feature.AvatarCardViewModel
import com.ocaterinca.ocaterinca.feature.AvatarService
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeInteractor
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeService
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeViewModel
import com.ocaterinca.ocaterinca.feature.question.QuestionViewModel
import com.ocaterinca.ocaterinca.feature.question.QuestionsInteractor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModules {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://e3tepsac4l.execute-api.eu-west-1.amazonaws.com/dev/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        }
                    )
                    .build()
            )
            .build()

    private val apiModules = module {
        single { retrofit.create(AvatarService::class.java) }
        single { retrofit.create(GameCodeService::class.java) }
    }

    private val repositoryModule = module {
//        single { PlayersRepository() }
    }

    private val interactorModule = module {
        single { AvatarCardInteractor(get()) }
        single { GameCodeInteractor(get()) }
        single { QuestionsInteractor(get()) }
    }

    private val viewModelModule = module {
        viewModel { AvatarCardViewModel(get()) }
        viewModel { GameCodeViewModel(get()) }
        viewModel { QuestionViewModel(get()) }
        viewModel { GameViewModel() }
    }

    val modules =
        listOf(apiModules, repositoryModule, interactorModule, viewModelModule)
}