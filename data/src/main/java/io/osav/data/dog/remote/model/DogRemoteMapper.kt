package io.osav.data.dog.remote.model

import io.osav.domain.usecase.dog.model.Dog

fun DogResponse.toDog() =
        Dog(
            text = text
        )