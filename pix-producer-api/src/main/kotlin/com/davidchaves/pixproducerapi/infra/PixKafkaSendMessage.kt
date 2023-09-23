package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.PixMessage
import com.davidchaves.pixproducerapi.data.protocols.PixSendMessage
import org.springframework.kafka.core.KafkaTemplate

class PixKafkaSendMessage(
    private val kafkaTemplate: KafkaTemplate<String, PixMessage>
) : PixSendMessage {

    override fun send(pix: PixMessage): PixMessage {
        kafkaTemplate.send("PIX_NEW_ORDER", pix.id, pix)
        return pix
    }
}