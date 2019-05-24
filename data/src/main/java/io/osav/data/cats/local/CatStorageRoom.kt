package io.osav.data.cats.local

import io.osav.data.cats.CatStorage
import io.osav.data.cats.local.model.toCat
import io.osav.data.cats.local.model.toCatRoom
import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CatStorageRoom(
    private val catDao: CatDao
) : CatStorage {

    override fun putCats(cats: List<Cat>): Single<List<Cat>> =
        Single.fromCallable {
            catDao.insert(cats.map { it.toCatRoom() })
            return@fromCallable cats
        }

    override fun getCats(): Single<List<Cat>> =
        catDao.getAll().map { list -> list.map { it.toCat() } }


    override fun observeCats(): Flowable<List<Cat>> =
        catDao.observeAll().map { list -> list.map { it.toCat() } }

    override fun clearStorage(): Completable =
            Completable.fromAction{ catDao.clearAll() }
}