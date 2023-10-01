package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.PixMessage
import com.davidchaves.pixproducerapi.data.protocols.PixMessageStatus
import com.davidchaves.pixproducerapi.data.protocols.PixSendMessage
import org.springframework.kafka.core.KafkaTemplate

class PixKafkaSendMessage(
    private val kafkaTemplate: KafkaTemplate<String, PixMessage>
) : PixSendMessage {

    override fun send(pix: PixMessage): PixMessage {
        when (pix.status) {
            PixMessageStatus.PENDING -> kafkaTemplate.send("PIX_NEW_ORDER", pix.id, pix)
            PixMessageStatus.ERROR -> kafkaTemplate.send("PIX_ORDER_ERROR", pix.id, pix)
            PixMessageStatus.FINISHED -> kafkaTemplate.send("PIX_FINISHED", pix.id, pix)
        }
        return pix
    }
}