package com.example.topic4chap4.dsprefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.jar.Attributes

class UserManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    private val NAME = stringPreferencesKey("user_name")
    private val AGE = intPreferencesKey("user_age")

    suspend fun saveData(name : String, umur : Int){
        context.dataStore.edit{
            it[NAME] = name
            it[AGE] = umur
        }
    }

    val userNama : Flow<String> = context.dataStore.data.map {
        it[NAME] ?:""
    }
    val userUmur : Flow<Int> = context.dataStore.data.map {
        it[AGE] ?:0
    }
    suspend fun deleteData(){
        context.dataStore.edit {
            it.clear()
        }
    }

}