package io.osav.data.info.remote

import io.osav.data.info.model.InfoResponse
import io.reactivex.Single

interface InfoRemote {

    fun getInfo(query: String): Single<InfoResponse>

}