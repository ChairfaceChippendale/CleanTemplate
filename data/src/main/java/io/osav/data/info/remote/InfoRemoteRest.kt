package io.osav.data.info.remote

import io.osav.data.info.remote.model.InfoResponse
import io.reactivex.Single

class InfoRemoteRest(
    private val logger: (String) -> Unit,
    private val api: InfoApi
): InfoRemote {

    override fun getInfo(query: String): Single<InfoResponse> {
        return api.getInfo(query)
    }
}