package com.davidchaves.pixconsumerapi.infra

import com.davidchaves.pixconsumerapi.data.protocols.PixClient
import com.davidchaves.pixconsumerapi.data.protocols.UpdatePixDto
import com.davidchaves.pixconsumerapi.exceptions.PixClientException
import retrofit2.Response

class PixClientImpl(
    private val pixRetrofitClient: PixRetrofitClient
) : PixClient {

    override suspend fun valid(sourceKey: String, targetKey: String) {
        val call = pixRetrofitClient.valid(sourceKey, targetKey)
        handleError(call)
    }

    override suspend fun update(updatePixDto: UpdatePixDto) {
        val call = pixRetrofitClient.update(updatePixDto)
        handleError(call)
    }

    private fun handleError(call: Response<Unit>) {
        if (!call.isSuccessful) {
            throw PixClientException(call.code(), call.message())
        }
    }
}