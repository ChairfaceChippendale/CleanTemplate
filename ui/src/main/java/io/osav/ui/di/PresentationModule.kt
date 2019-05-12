package io.osav.ui.di

import io.osav.domain.executor.PostExecutionThread
import io.osav.ui.UIThread
import org.koin.dsl.module


val presentationModule = module {

    single <PostExecutionThread>{ UIThread() }
}