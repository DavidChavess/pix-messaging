package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.DbFindPixByIdUseCase.Output
import com.davidchaves.pixproducerapi.application.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.application.protocols.UseCase
import com.davidchaves.pixproducerapi.domain.entity.Pix
import com.davidchaves.pixproducerapi.domain.entity.Pix.Status
import java.math.BigDecimal
import java.time.LocalDateTime

class DbFindPixByIdUseCase(
    private val findPixByIdRepository: FindPixByIdRepository
) : UseCase<String, Output> {

    data class Output(
        val id: String,
        val targetKey: String,
        val sourceKey: String,
        val value: BigDecimal,
        val transferDate: LocalDateTime,
        val status: Status,
    )

    override fun execute(input: String): Output {
        return findPixByIdRepository.findById(input)
            .let { Output(it.id, it.targetKey, it.sourceKey, it.value, it.transferDate, Pix.Status.valueOf(it.status.name)) }
    }
}