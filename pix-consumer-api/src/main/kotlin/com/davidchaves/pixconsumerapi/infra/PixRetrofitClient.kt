package com.davidchaves.pixconsumerapi.infra

import com.davidchaves.pixconsumerapi.data.protocols.PixDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PixRetrofitClient {

    @POST("/pix/valid")
    fun valid(@Query("sourceKey") sourceKey: String, @Query("targetKey") targetKey: String): Call<Unit>

    @POST("/pix")
    fun update(@Body pixDto: PixDto): Call<Unit>
}