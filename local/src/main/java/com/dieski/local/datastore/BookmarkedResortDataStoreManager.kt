package com.dieski.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/30
 */
class BookmarkedResortDataStoreManager @Inject constructor(
	appContext: Context
) {
	private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
		BOOKMARKED_RESORT_ID_LIST_DATASTORE_NAME
	)

	private val dataStore = appContext.dataStore

	val bookmarkedResortIdSet: Flow<Set<Long>> =
		dataStore.data.mapNotNull { preferences ->
			preferences[stringPreferencesKey(BOOKMARKED_RESORT_ID_LIST_DATASTORE_KEY)]
				?.convertToLongSet() ?: emptySet()
		}

	suspend fun saveResortBookmark(resortId: Long) {
		dataStore.edit { preferences ->
			preferences[stringPreferencesKey(BOOKMARKED_RESORT_ID_LIST_DATASTORE_KEY)] =
				bookmarkedResortIdSet.first().toMutableSet().apply { add(resortId) }
					.convertToJsonString()
		}
	}

	suspend fun deleteResortBookmark(resortId: Long) {
		dataStore.edit { preferences ->
			preferences[stringPreferencesKey(BOOKMARKED_RESORT_ID_LIST_DATASTORE_KEY)] =
				bookmarkedResortIdSet.first().toMutableSet().apply { remove(resortId) }
					.convertToJsonString()
		}
	}

	companion object {
		private const val BOOKMARKED_RESORT_ID_LIST_DATASTORE_NAME = "resort_bookmark"
		const val BOOKMARKED_RESORT_ID_LIST_DATASTORE_KEY = "bookmarked_resort_id_list_datastore_key"
	}

	private fun String.convertToLongSet(): Set<Long> {
		val json = Json { ignoreUnknownKeys = true } // JSON 파싱 설정
		return json.decodeFromString<List<Long>>(this).toSet() // JSON 문자열 -> List<Long>
	}

	private fun Set<Long>.convertToJsonString(): String {
		val json = Json { prettyPrint = true } // JSON 생성 설정
		return json.encodeToString(this).toString() // Set<Long> -> JSON 문자열
	}
}