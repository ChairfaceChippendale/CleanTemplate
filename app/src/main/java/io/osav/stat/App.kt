package io.osav.stat

import android.app.Application
import io.osav.stat.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            appModule
        }
    }
}