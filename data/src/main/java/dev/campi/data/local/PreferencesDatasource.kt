package dev.campi.data.local

import kotlinx.coroutines.flow.Flow

interface PreferencesDatasource {

    fun getLastUpdatedTime(): Flow<Long>

    suspend fun setLastUpdatedTime(time: Long)
}