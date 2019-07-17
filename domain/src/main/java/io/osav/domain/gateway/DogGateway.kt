package io.osav.domain.gateway

import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Maybe

interface DogGateway {

    fun getDog(query: String): Maybe<Dog>

}