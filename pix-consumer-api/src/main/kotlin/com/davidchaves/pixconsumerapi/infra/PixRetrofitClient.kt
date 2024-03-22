package com.davidchaves.pixconsumerapi.infra

import com.davidchaves.pixconsumerapi.data.protocols.UpdatePixDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PixRetrofitClient {

    @POST("/pix/valid")
    suspend fun valid(@Query("sourceKey") sourceKey: String, @Query("targetKey") targetKey: String): Response<Unit>

    @POST("/pix")
    suspend fun update(@Body updatePixDto: UpdatePixDto): Response<Unit>
}