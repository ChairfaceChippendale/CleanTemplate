package io.osav.data.di

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import io.osav.data.JobExecutor
import io.osav.data.info.InfoGatewayImpl
import io.osav.data.info.remote.InfoRemoteRest
import io.osav.data.info.storage.InfoStorage
import io.osav.data.info.storage.InfoStoragePref
import io.osav.domain.executor.ThreadExecutor
import io.osav.domain.gateway.info.InfoGateway
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<ThreadExecutor> { JobExecutor() }

    single(named("gson")) { Gson() }

    single { InfoGatewayImpl(get(named("data")), get()) as InfoGateway }
    single { InfoRemoteRest(get(named("data")), get()) }
    single { InfoStoragePref(get(named("info_pref")), get(), get(named("storage_ex"))) }

    single<SharedPreferences> (named("info_pref")){
        BinaryPreferencesBuilder(androidContext())
            .name("info")
            .exceptionHandler(get(named("pref_ex")))
            .build()
    }

}