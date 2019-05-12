package io.osav.data.di

import com.google.gson.Gson
import io.osav.data.JobExecutor
import io.osav.data.info.InfoGatewayImpl
import io.osav.domain.executor.ThreadExecutor
import io.osav.domain.gateway.info.InfoGateway
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single <ThreadExecutor>{ JobExecutor() }

    single { Gson() }

    single { InfoGatewayImpl(get(named("DATA")), get(named("DATA_EX"))) as InfoGateway }
}