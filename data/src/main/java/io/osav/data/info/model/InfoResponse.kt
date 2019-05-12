package io.osav.data.info.model

import com.google.gson.annotations.SerializedName

data class InfoResponse (
    @SerializedName("text")
    val text: String
)