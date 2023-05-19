package com.ex.domain.repository

import androidx.paging.PagingSource
import com.ex.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getBeers() : PagingSource<Int, Beer>
    fun getBeer(beerId : Int) : Flow<Beer>
}