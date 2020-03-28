package com.ocaterinca.ocaterinca

import android.app.Application
import com.ocaterinca.ocaterinca.data.di.AppModules
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin

class Caterincapp : Application() {

    override fun onCreate() {
        super.onCreate()
        initPrefs()
        initDI()
    }

    private fun initPrefs() {
        Hawk.init(this).build()
    }

    private fun initDI() {
        startKoin {
            modules(AppModules.modules)
        }
    }
}