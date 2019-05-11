package io.osav.data.di

import com.google.gson.Gson
import io.osav.data.JobExecutor
import io.osav.domain.executor.ThreadExecutor
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module = module {

    single <ThreadExecutor>{ JobExecutor() }

    single { Gson() }
}