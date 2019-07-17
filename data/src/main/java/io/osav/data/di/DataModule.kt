package io.osav.data.di

import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import io.osav.data.AppDatabase
import io.osav.data.JobExecutor
import io.osav.data.cats.CatGatewayImpl
import io.osav.data.cats.local.CatStorageRoom
import io.osav.data.dog.DogGatewayImpl
import io.osav.data.dog.remote.DogRemoteRest
import io.osav.data.dog.local.DogStoragePref
import io.osav.domain.executor.ThreadExecutor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<ThreadExecutor> { JobExecutor() }

    single(named("gson")) { Gson() }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "stats_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { DogGatewayImpl(get(named("log_data")), get()) }
    single { DogRemoteRest(get(named("log_data")), get()) }
    single { DogStoragePref(get(named("dog_pref")), get(), get(named("log_ex_storage"))) }
    single<SharedPreferences> (named("dog_pref")){
        BinaryPreferencesBuilder(androidContext())
            .name("info")
            .exceptionHandler(get(named("log_ex_pref")))
            .build()
    }

    single { CatGatewayImpl(get()) }
    single { CatStorageRoom(get()) }
    single { get<AppDatabase>().getCatDao() }

}