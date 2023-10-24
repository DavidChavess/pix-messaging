package com.davidchaves.pixconsumerapi.infra

import com.davidchaves.pixconsumerapi.data.protocols.PixClient
import com.davidchaves.pixconsumerapi.data.protocols.PixDto
import com.davidchaves.pixconsumerapi.exceptions.PixClientException
import retrofit2.Response

class PixClientImpl(
    private val pixRetrofitClient: PixRetrofitClient
) : PixClient {

    override fun valid(sourceKey: String, targetKey: String) {
        val call = pixRetrofitClient.valid(sourceKey, targetKey).execute()
        handleError(call)
    }

    override fun update(pixDto: PixDto) {
        val call = pixRetrofitClient.update(pixDto).execute()
        handleError(call)
    }

    private fun handleError(call: Response<Unit>) {
        if (!call.isSuccessful) {
            throw PixClientException(call.code(), call.message())
        }
    }
}