package com.ex.domain.usecase

import com.ex.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeerListUseCase @Inject constructor(private val repository: BeerRepository) {
    operator fun invoke() = repository.getBeers()
}