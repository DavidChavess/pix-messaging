package com.davidchaves.pixconsumerapi.main.consumers

import com.davidchaves.pixconsumerapi.domain.PixMessage
import com.davidchaves.pixconsumerapi.domain.PixValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(name = ["kafka-streams.enabled"], havingValue = "false")
class PixValidatorMessagingConsumer(
    private val pixValidator: PixValidator
) {

    @KafkaListener(
        topics = ["PIX_NEW_ORDER"],
        groupId = "PixValidatorMessaging",
        containerFactory = "listenerContainerFactory"
    )
    fun process(pixMessage: PixMessage, acknowledgment: Acknowledgment) {
        println("PixValidatorMessagingConsumer")

        CoroutineScope(Dispatchers.IO).launch {
            println("processando pix $pixMessage...")
            pixValidator.process(pixMessage)
            acknowledgment.acknowledge()
        }
    }
}