package io.osav.domain.gateway.info

import io.osav.domain.usecase.info.model.Info
import io.reactivex.Maybe

interface InfoGateway {

    fun getInfo(query: String): Maybe<Info>

}