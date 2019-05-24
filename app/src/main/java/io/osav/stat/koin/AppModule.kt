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

    single(named("log_net")){ Looog.i(tag = "NET")}
    single(named("log_room")){ Looog.d(tag = "ROOM")}
    single(named("log_data")){ Looog.d(tag = "DATA")}
    single(named("log_ex_data")){ Looog.ex(tag = "DATA")}
    single(named("log_ex_storage")){ Looog.ex(tag = "STORE")}
    single(named("log_ex_pref")){ Looog.th(tag = "STORE")}

}