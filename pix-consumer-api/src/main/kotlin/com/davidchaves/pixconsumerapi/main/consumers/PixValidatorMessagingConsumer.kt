package com.davidchaves.pixconsumerapi.main.consumers

import com.davidchaves.pixconsumerapi.domain.PixMessage
import com.davidchaves.pixconsumerapi.domain.PixValidator
import kotlinx.coroutines.*
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class PixValidatorMessagingConsumer(
    private val pixValidator: PixValidator
) {

    @KafkaListener(topics = ["PIX_NEW_ORDER"], groupId = "PixValidatorMessaging", containerFactory = "listenerContainerFactory")
    fun process(pixMessage: PixMessage) {
        CoroutineScope(Dispatchers.IO).launch {
            println("processando pix $pixMessage...")
            pixValidator.process(pixMessage)
        }
    }
}