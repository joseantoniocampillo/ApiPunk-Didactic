package dev.campi.apipunksolution.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.campi.apipunk.domain.accesories.BeerFilter
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.usecases.UseCases
import dev.campi.apipunksolution.usecases.sortedByBeerFilter
import dev.campi.apipunksolution.util.Constants.MILLIS_PER_DAY
import dev.campi.data.dispatchers.DispatchersProvides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Se usa paging para remoto por motivos didácticos en la primera carga.
 * Para la api en cuestión con sólo 325 registros no sería necesario.
 * Para las siguientes llamadas. Y búsquedas. Se usa el cacheado que se guarda en base de datos
 * Esto también es por lo limitado que esta la api en cuanto a filtros que sí podemos aplicar desde la base de datos.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    private val dispatchers: DispatchersProvides,
    private val pagingData: Flow<PagingData<BeerType>>
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _pagingRemoteDataBeer = MutableStateFlow<PagingData<BeerType>>(PagingData.empty())
    val pagingRemoteDataBeer = _pagingRemoteDataBeer.asStateFlow()

    private val _localBeerList = mutableStateOf<List<BeerType>?>(null)
    val localBeerList: State<List<BeerType>?> = _localBeerList

    private val _beerSelected = mutableStateOf<BeerType?>(null)
    val beerSelected: State<BeerType?> = _beerSelected

    init {
        showRemotePaging() // Uso de paging con fines didácticos no sería necesario para esta api
        updateDatabase()
    }

    private fun showRemotePaging() {
        viewModelScope.launch(dispatchers.io) {
            pagingData.cachedIn(viewModelScope).collect {
                _pagingRemoteDataBeer.value = it
            }
        }
    }

    private fun updateDatabase() {
        viewModelScope.launch(dispatchers.io) {
            useCases.updateDatabase(cachePolicy = MILLIS_PER_DAY)
        }

    }

    fun searchBeer(query: String) {
        viewModelScope.launch(dispatchers.io) {
            useCases.getAllBeersContain(query)?.let {
                _localBeerList.value = it
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun applyFilter(beerFilter: BeerFilter) {
        viewModelScope.launch(dispatchers.io) {
            val localList = localBeerList.value ?: useCases.getAllBeers() ?: return@launch
            val filtered = localList.sortedByBeerFilter(beerFilter)
            _localBeerList.value = filtered
        }
    }

    fun onBeerSelectedChange(beerType: BeerType?) {
        _beerSelected.value = beerType
    }
}
