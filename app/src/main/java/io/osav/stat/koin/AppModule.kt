package io.osav.stat.koin

import io.osav.data.di.dataModule
import io.osav.data.di.remoteModule
import io.osav.domain.di.domainModule
import io.osav.stat.tools.Looog
import io.osav.ui.di.presentationModule
import io.osav.ui.di.viewModelModule
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModules: List<Module>
    get() = listOf(
        coreModule,

        domainModule,

        dataModule,
        remoteModule,

        viewModelModule,
        presentationModule
    )

val coreModule = module {

    factory { CompositeDisposable() }

    single(named("net")){ Looog.i(tag = "NET")}
    single(named("room")){ Looog.d(tag = "ROOM")}
    single(named("data")){ Looog.d(tag = "DATA")}
    single(named("data_ex")){ Looog.ex(tag = "DATA")}
    single(named("storage_ex")){ Looog.ex(tag = "STORE")}
    single(named("pref_ex")){ Looog.th(tag = "STORE")}

}