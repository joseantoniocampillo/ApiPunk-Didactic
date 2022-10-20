package dev.campi.apipunksolution.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.usecases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _beerList = MutableStateFlow<List<BeerType>>(emptyList())
    val beerList = _beerList.asStateFlow()

    init {
        viewModelScope.launch {
            useCases.getAllBeers()?.let { list ->
                _beerList.value = list
            }
        }
    }
}