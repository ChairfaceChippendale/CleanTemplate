package io.osav.ui.di

import io.osav.domain.executor.PostExecutionThread
import io.osav.ui.UIThread
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule: Module = module {

    single <PostExecutionThread>{ UIThread() }
}