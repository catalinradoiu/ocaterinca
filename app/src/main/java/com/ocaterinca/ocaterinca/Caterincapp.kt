package com.ocaterinca.ocaterinca

import android.app.Application
import com.ocaterinca.ocaterinca.data.di.AppModules
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin
import timber.log.Timber

class Caterincapp : Application() {

    companion object {
        lateinit var instance: Caterincapp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initPrefs()
        initDI()
        initLogging()
    }

    private fun initPrefs() {
        Hawk.init(this).build()
    }

    private fun initDI() {
        startKoin {
            modules(AppModules.modules)
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}