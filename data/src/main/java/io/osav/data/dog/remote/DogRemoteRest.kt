package io.osav.data.dog.remote

import io.osav.data.dog.DogRemote
import io.osav.data.dog.remote.model.toDog
import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Single

class DogRemoteRest(
    private val logger: (String) -> Unit,
    private val api: DogApi
): DogRemote {

    override fun fetchDog(query: String): Single<Dog> {
        return api.fetchDog(query).map { it.toDog() }
    }
}