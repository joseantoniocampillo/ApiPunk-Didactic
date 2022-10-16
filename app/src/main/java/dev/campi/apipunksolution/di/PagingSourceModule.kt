package dev.campi.apipunksolution.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.data.pager.NETWORK_PAGE_SIZE
import dev.campi.apipunksolution.data.pager.PagingSource
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ViewModelComponent::class)
object PagingSourceModule {

    @Provides
    @ViewModelScoped
    fun providePagerAsFlow(
        pagingSource: PagingSource
    ): Flow<PagingData<BeerType>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { pagingSource }
    ).flow
}