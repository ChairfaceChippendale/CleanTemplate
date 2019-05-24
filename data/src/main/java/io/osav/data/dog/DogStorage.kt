package io.osav.data.dog

import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface DogStorage {

    fun putDog(dog: Dog): Single<Dog>

    fun getDog(): Single<Dog>

    fun observeDog(): Flowable<Dog>

    fun clearStorage(): Completable
}