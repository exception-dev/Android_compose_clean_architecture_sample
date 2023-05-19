package com.ex.data.repository

import com.ex.data.api.ApiService
import com.ex.data.paging.BeerPagingSource
import com.ex.domain.model.Beer
import com.ex.domain.repository.BeerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BeerRepositoryImpl  @Inject constructor(private val apiService: ApiService) : BeerRepository {
    override fun getBeers() = BeerPagingSource(apiService = apiService)
    override fun getBeer(beerId : Int): Flow<Beer> = flow{
        val response = apiService.beerInfo(beerId)
        println("response.isSuccessful : ${response.isSuccessful}")
        if(response.isSuccessful){
            val data = response.body()?.get(0)
            data?.let{
                emit(it)
                return@flow
            }
        }else{
            //TODO. exception 처리후 화면상에서 해당 에러 처리
            //throw Exception("통신에러")
        }
    }
}