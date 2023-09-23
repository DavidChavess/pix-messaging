package com.davidchaves.pixproducerapi.domain.usecase

import com.davidchaves.pixproducerapi.domain.model.Pix
import java.math.BigDecimal
import java.time.LocalDateTime

enum class PixDtoStatus {
    PENDING,
    FINISHED,
    ERROR
}

data class PixDto(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime = LocalDateTime.now(),
    val status: PixDtoStatus = PixDtoStatus.PENDING,
)

interface CreatePixUseCase {
    fun create(pixDto: PixDto): Pix
}