package com.ex.domain.usecase

import com.ex.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(private val repository: BeerRepository) {
    operator fun invoke(beerId: Int) = repository.getBeer(beerId)
}