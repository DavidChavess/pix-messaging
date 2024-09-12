package com.davidchaves.pixproducerapi.domain.entity

import com.davidchaves.pixproducerapi.domain.event.Aggregate
import com.davidchaves.pixproducerapi.domain.event.DomainEvent
import com.davidchaves.pixproducerapi.domain.event.PixPendingEvent
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Pix(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: Status
) : Aggregate() {

    enum class Status(val eventName: String) {
        PENDING("PIX_NEW_ORDER"),
        FINISHED("PIX_ORDER_ERROR"),
        ERROR("PIX_FINISHED")
    }

    companion object {
        fun create(
            targetKey: String,
            sourceKey: String,
            value: BigDecimal,
            transferDate: LocalDateTime = LocalDateTime.now(),
            callback: (event: DomainEvent) -> Unit = {}
        ): Pix {
            val id = UUID.randomUUID().toString()
            val status = Status.PENDING
            val pix = Pix(id, targetKey, sourceKey, value, transferDate, status)
            pix.register(pix.status.eventName, callback)
            pix.notify(
                status.eventName,
                PixPendingEvent(
                    pix.id,
                    pix.targetKey,
                    pix.sourceKey,
                    pix.value,
                    pix.transferDate,
                    pix.status,
                    pix.status.eventName
                )
            )
            return pix
        }
    }
}
