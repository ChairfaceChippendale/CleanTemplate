package io.osav.data.dog

import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Single

interface DogRemote {

    fun fetchDog(query: String): Single<Dog>

}