package io.osav.data.dog.remote.model

import com.google.gson.annotations.SerializedName

data class DogResponse (
    @SerializedName("text")
    val text: String
)