package com.example.data.network.repository

import com.example.data.network.api.SkyengApi
import com.example.data.network.api.safeApi
import com.example.domain.models.Translation
import com.example.domain.repository.NetworkRepository
import com.example.domain.response.Result

class NetworkRepositoryImpl(
    private val api: SkyengApi
) : NetworkRepository {
    override suspend fun translate(query: String): Result<Translation> = safeApi {
        val res = api.search(query)
        Translation(
            text = res[0].meanings[0].translation.text,
            note = res[0].meanings[0].translation.note
        )
    }
}
