package io.osav.data.dog

import io.osav.domain.gateway.DogGateway
import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Maybe


class DogGatewayImpl(
    private val logger: (String) -> Unit,
    private val dogRemote: DogRemote
): DogGateway {

    override fun getDog(query: String): Maybe<Dog> {
        return dogRemote.fetchDog(query)
            .toMaybe()
    }
}