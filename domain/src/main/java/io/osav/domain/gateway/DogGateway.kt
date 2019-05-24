package io.osav.domain.gateway

import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Maybe

interface DogGateway {

    fun getInfo(query: String): Maybe<Dog>

}