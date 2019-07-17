package io.osav.data.cats

import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface CatStorage {

    fun putCats(cats: List<Cat>): Single<List<Cat>>

    fun getCats(): Single<List<Cat>>

    fun observeCats(): Flowable<List<Cat>>

    fun clearStorage(): Completable

    fun getCatById(id: String): Maybe<Cat>
}