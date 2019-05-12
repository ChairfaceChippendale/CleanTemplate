package io.osav.domain.gateway.info

import io.reactivex.Maybe

interface InfoGateway {

    fun getInfo(): Maybe<String>

}