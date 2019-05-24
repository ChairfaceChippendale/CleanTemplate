package io.osav.domain.gateway

import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Flowable
import io.reactivex.Single

interface CatGateway {

    fun putCats(cats: List<Cat>): Single<List<Cat>>

    fun getCats(): Single<List<Cat>>

    fun observeCats(): Flowable<List<Cat>>

}