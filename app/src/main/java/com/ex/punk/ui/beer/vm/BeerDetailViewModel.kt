package com.ex.punk.ui.beer.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ex.domain.model.Beer
import com.ex.domain.usecase.GetBeerListUseCase
import com.ex.domain.usecase.GetBeerUseCase
import com.ex.punk.ui.base.vm.BaseViewModel
import com.ex.punk.ui.main.BeerDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(private val arguments: SavedStateHandle, private val getBeerUseCase: GetBeerUseCase) : BaseViewModel(){

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getData(){
        val beerId = arguments.get<Int>(BeerDetail.beerArg) ?: 0

        _uiState.value = UiState.Loading
        viewModelScope.launch {
            getBeerUseCase(beerId).catch {
                _uiState.value = UiState.Error(it)
            }.collect{ beer ->
                _uiState.value = UiState.Success(beer)
            }
        }
    }

    sealed class UiState {
        object Loading: UiState()
        data class Success(val beer: Beer): UiState()
        data class Error(val exception: Throwable): UiState()
    }

}