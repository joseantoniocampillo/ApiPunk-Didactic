package dev.campi.apipunksolution.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.campi.apipunksolution.util.Constants.LAST_UPDATED_PREFS_KEY
import dev.campi.apipunksolution.util.Constants.PREFERENCES_PUNK
import dev.campi.apipunksolution.util.Constants.UNIX_EPOC_LONG
import dev.campi.data.local.PreferencesDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDatasourceImpl @Inject constructor(
    private val context: Context
): PreferencesDatasource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_PUNK)

    private val longPrefKey = longPreferencesKey(LAST_UPDATED_PREFS_KEY)

    override fun getLastUpdatedTime(): Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[longPrefKey] ?: UNIX_EPOC_LONG
    }

    override suspend fun setLastUpdatedTime(time: Long) {
        context.dataStore.edit { lastUpdate ->
            lastUpdate[longPrefKey] = time
        }
    }
}