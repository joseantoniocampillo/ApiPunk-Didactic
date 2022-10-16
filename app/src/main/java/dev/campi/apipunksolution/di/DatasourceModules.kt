package dev.campi.apipunksolution.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.campi.apipunksolution.data.local.LocalDatasourceImpl
import dev.campi.apipunksolution.data.server.RemoteDataSourceImpl
import dev.campi.data.local.LocalDatasource
import dev.campi.data.remote.RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinderForRemoteDataSource {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinderForLocalDataSource {

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDatasourceImpl): LocalDatasource
}