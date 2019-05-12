package io.osav.data.info

import io.osav.domain.gateway.info.InfoGateway
import io.reactivex.Maybe
import java.lang.IllegalStateException

class InfoGatewayImpl(
    val log: (String) -> Unit,
    val exLog: (String, Throwable) -> Unit
): InfoGateway {

    override fun getInfo(): Maybe<String> {
        log("Hello")
        exLog("Gabela", IllegalStateException("Fatal"))
        return Maybe.just("Yes")
    }
}