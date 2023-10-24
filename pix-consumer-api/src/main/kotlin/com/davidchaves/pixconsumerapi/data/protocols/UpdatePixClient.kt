package com.davidchaves.pixconsumerapi.data.protocols

import java.math.BigDecimal

enum class UpdatePixStatus { PENDING, FINISHED, ERROR }

data class PixDto(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val status: UpdatePixStatus
)

interface UpdatePixClient {
    fun update(pixDto: PixDto)
}