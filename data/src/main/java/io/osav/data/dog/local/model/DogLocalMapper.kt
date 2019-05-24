package io.osav.data.dog.local.model

import io.osav.domain.usecase.dog.model.Dog

fun DogPref.toDog() =
        Dog(
            text = text
        )

fun Dog.toDogPref() =
        DogPref(
            text = text
        )
