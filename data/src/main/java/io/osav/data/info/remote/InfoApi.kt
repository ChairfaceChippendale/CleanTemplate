package io.osav.data.info.remote

import io.osav.data.info.remote.model.InfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface InfoApi{

    @GET("info/")
    fun getInfo(
        @Query("query") query: String
    ) : Single<InfoResponse>

}