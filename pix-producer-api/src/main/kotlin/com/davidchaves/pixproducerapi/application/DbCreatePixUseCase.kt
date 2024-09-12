package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.DbCreatePixUseCase.Input
import com.davidchaves.pixproducerapi.application.DbCreatePixUseCase.Output
import com.davidchaves.pixproducerapi.domain.entity.Pix
import com.davidchaves.pixproducerapi.domain.entity.Pix.Status
import com.davidchaves.pixproducerapi.domain.event.DomainEvent
import com.davidchaves.pixproducerapi.application.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.application.protocols.EventEmitter
import com.davidchaves.pixproducerapi.application.protocols.UseCase
import java.math.BigDecimal
import java.time.LocalDateTime

class DbCreatePixUseCase(
    private val createPixRepository: CreatePixRepository,
    private val eventEmitter: EventEmitter<DomainEvent>
) : UseCase<Input, Output> {

    data class Input(
        val targetKey: String,
        val sourceKey: String,
        val value: BigDecimal,
    )

    data class Output(
        val id: String,
        val targetKey: String,
        val sourceKey: String,
        val value: BigDecimal,
        val transferDate: LocalDateTime,
        val status: Status,
    )

    override fun execute(input: Input): Output {
        val pix = Pix.create(input.targetKey, input.sourceKey, input.value) {
            eventEmitter.emit(it.eventName, it)
        }
        createPixRepository.add(pix)
        return Output(
            id = pix.id,
            targetKey = pix.targetKey,
            sourceKey = pix.sourceKey,
            value = pix.value,
            transferDate = pix.transferDate,
            status = pix.status
        )
    }
}