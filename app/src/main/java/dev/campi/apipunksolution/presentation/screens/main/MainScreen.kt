package dev.campi.apipunksolution.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.presentation.screens.details.BeerDetailScreen
import dev.campi.apipunksolution.presentation.screens.search.SearchTopBar
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    ListContent(mainViewModel = mainViewModel)
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ListContent(
    mainViewModel: MainViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val searchQuery by mainViewModel.searchQuery
    val remotePagedItems: LazyPagingItems<BeerType> = mainViewModel.pagingRemoteDataBeer.collectAsLazyPagingItems()
    val beerSelected: BeerType? by mainViewModel.beerSelected
    val localListBeer: List<BeerType>? by mainViewModel.localBeerList

    beerSelected?.let {
        BeerDetailScreen(it) {
            mainViewModel.onBeerSelectedChange(null)
        }
    } ?: run {
        Scaffold(
            topBar = {
                SearchTopBar(
                    text = searchQuery,
                    onTextChange = {
                        mainViewModel.updateSearchQuery(query = it)
                        mainViewModel.searchBeer(query = it)
                    },
                    onSearchClicked = {
                        keyboardController?.hide()
                    },
                    onFilterSelected = {
                        mainViewModel.applyFilter(it)
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = 0)
                        }
                    }
                )
            },
            content = { padding: PaddingValues ->
                val lazyColumnModifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.White)
                localListBeer?.let { list ->
                    LocalBeerLazyColumn(lazyColumnModifier, listState, list) {
                        mainViewModel.onBeerSelectedChange(it)
                    }
                } ?: run {
                    RemotePagingLazyColumn(lazyColumnModifier, listState, remotePagedItems) {
                        mainViewModel.onBeerSelectedChange(it)
                    }
                }
            }
        )
    }
}

@Composable
fun LocalBeerLazyColumn(
    modifier: Modifier,
    listState: LazyListState,
    localList: List<BeerType>,
    beerSelected: (BeerType) -> Unit
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(localList) {
            BeerItem(it, beerSelected)
        }
    }
}

@Composable
fun RemotePagingLazyColumn(
    modifier: Modifier,
    listState: LazyListState,
    searchedBeerPaggingItems: LazyPagingItems<BeerType>,
    beerSelected: (BeerType) -> Unit
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(searchedBeerPaggingItems) { item ->
            item?.let { beer ->
                BeerItem(beer, beerSelected)
            }
        }
    }
}
