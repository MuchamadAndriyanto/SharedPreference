package com.example.topic4chap4.dsproto

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.topic4chap4.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserPreferencesRepository(private val context: Context) {
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserPreferencesSerializer
    )
    suspend fun saveData(nama: String, umur: Int) {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().setNama(nama).build()
            preferences.toBuilder().setUmur(umur).build()
        }
    }
    suspend fun clearData() {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().clearNama().build()
            preferences.toBuilder().clearUmur().build()
        }
    }
    //readdata
    val userPreferencesFlow: Flow<UserPreferences> = context.userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("TAG", "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

}