package io.osav.stat

import android.app.Application
import io.osav.stat.koin.appModules
import io.osav.stat.tools.Looog
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            properties(mapOf(
                "debug" to BuildConfig.DEBUG, //"debug" property let us know app build type in any module
                "base_url" to "url"
            ))
            modules(appModules)
        }

        Looog.init()
    }
}