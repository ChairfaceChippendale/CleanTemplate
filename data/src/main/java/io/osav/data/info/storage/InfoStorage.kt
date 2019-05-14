package io.osav.data.info.storage

import io.osav.data.info.storage.model.InfoStored
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface InfoStorage {

    fun putInfo(info: InfoStored): Single<InfoStored>

    fun getInfo(): Single<InfoStored>

    fun observeInfo(): Flowable<InfoStored>

    fun clearStorage(): Completable
}