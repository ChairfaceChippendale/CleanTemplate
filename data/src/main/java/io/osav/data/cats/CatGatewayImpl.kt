package io.osav.data.cats

import io.osav.domain.gateway.CatGateway
import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Flowable
import io.reactivex.Single

class CatGatewayImpl(
    private val catStorage: CatStorage
) : CatGateway {

    override fun putCats(cats: List<Cat>): Single<List<Cat>> =
        catStorage.putCats(cats)

    override fun getCats(): Single<List<Cat>> =
        catStorage.getCats()

    override fun observeCats(): Flowable<List<Cat>> =
        catStorage.observeCats()
}