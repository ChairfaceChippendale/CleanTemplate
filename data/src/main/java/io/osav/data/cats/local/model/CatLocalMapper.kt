package io.osav.data.cats.local.model

import io.osav.domain.usecase.cat.model.Cat

fun Cat.toCatRoom() =
        CatRoom(
            id = id,
            name = name
        )

fun CatRoom.toCat() =
    Cat(
        id = id,
        name = name
    )