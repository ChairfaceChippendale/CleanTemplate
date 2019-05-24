package io.osav.data.cats.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CatRoom (
    @PrimaryKey
    val id: String,
    val name: String
)