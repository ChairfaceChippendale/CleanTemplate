package io.osav.data.info

import io.osav.data.info.remote.model.InfoResponse
import io.osav.domain.usecase.info.model.Info

fun InfoResponse.toInfo() =
        Info(
            text = text
        )