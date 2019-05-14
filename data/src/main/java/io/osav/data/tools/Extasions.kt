package io.osav.data.tools

import com.google.gson.JsonParseException


inline fun <T> catchGson(logger: (String, Throwable) -> Unit = { _, _ -> }, action: () -> T): T? {
    return try {
        action()
    } catch (t: JsonParseException) {
        logger("Can't parse json", t)
        null
    }
}


