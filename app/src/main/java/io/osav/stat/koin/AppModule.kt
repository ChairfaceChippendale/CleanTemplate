package io.osav.stat.koin

import io.osav.data.di.dataModule
import io.osav.domain.di.domainModule
import io.osav.ui.di.presentationModule
import io.osav.ui.di.viewModelModule
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: List<Module>
    get() = listOf(
        domainModule,
        dataModule,
        viewModelModule,
        presentationModule
    )

val coreModule: Module = module {

    factory { CompositeDisposable() }

}