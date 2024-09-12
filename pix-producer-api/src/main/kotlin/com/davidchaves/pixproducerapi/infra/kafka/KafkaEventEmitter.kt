package com.davidchaves.pixproducerapi.infra.kafka

import com.davidchaves.pixproducerapi.application.protocols.EventEmitter
import org.springframework.kafka.core.KafkaTemplate

class KafkaEventEmitter<Message>(
    private val kafkaTemplate: KafkaTemplate<String, Message>
) : EventEmitter<Message> {

    override fun emit(queue: String, message: Message) {
        kafkaTemplate.send(queue, message)
    }
}