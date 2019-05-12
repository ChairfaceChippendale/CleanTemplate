package io.osav.stat.koin

import io.osav.data.di.dataModule
import io.osav.domain.di.domainModule
import io.osav.stat.tools.Looog
import io.osav.ui.di.presentationModule
import io.osav.ui.di.viewModelModule
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule: List<Module>
    get() = listOf(
        coreModule,
        domainModule,
        dataModule,
        viewModelModule,
        presentationModule
    )

val coreModule = module {

    factory { CompositeDisposable() }

    single(named("NET")){ Looog.i(tag = "NET")}
    single(named("ROOM")){ Looog.d(tag = "ROOM")}
    single(named("DATA")){ Looog.d(tag = "DATA")}
    single(named("DATA_EX")){ Looog.ex(tag = "DATA")}
}