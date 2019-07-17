package io.osav.domain.di

import io.osav.domain.usecase.cat.GetAllCatsUseCase
import io.osav.domain.usecase.cat.GetCatUseCase
import io.osav.domain.usecase.dog.GetDogUseCase
import org.koin.dsl.module


val domainModule = module {

    factory { GetCatUseCase(get(), get(), get(), get()) }
    factory { GetAllCatsUseCase(get(), get(), get(), get()) }

    factory { GetDogUseCase(get(), get(), get(), get()) }

}