package io.osav.data.info

import io.osav.data.info.remote.InfoRemote
import io.osav.domain.gateway.info.InfoGateway
import io.osav.domain.usecase.info.model.Info
import io.reactivex.Maybe


class InfoGatewayImpl(
    private val logger: (String) -> Unit,
    private val infoRemote: InfoRemote
): InfoGateway {

    override fun getInfo(query: String): Maybe<Info> {
        return infoRemote.getInfo(query)
            .map { it.toInfo() }
            .toMaybe()
    }
}