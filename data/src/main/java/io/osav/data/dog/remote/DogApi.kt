package io.osav.data.dog.remote

import io.osav.data.dog.remote.model.DogResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi{

    @GET("dog/")
    fun fetchDog(
        @Query("query") query: String
    ) : Single<DogResponse>

}