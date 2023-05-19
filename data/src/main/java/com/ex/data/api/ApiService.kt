package com.ex.data.api

import com.ex.data.common.Constants
import com.ex.domain.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("beers")
    suspend fun beers(@Query("page") page : Int, @Query("per_page") pageSize : Int = Constants.PAGE_SIZE): Response<MutableList<Beer>>

    @GET("beers/{id}")
    suspend fun beerInfo(@Path("id") beerId: Int): Response<List<Beer>>

}