package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.PixMessage
import com.davidchaves.pixproducerapi.data.protocols.PixMessageStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.kafka.core.KafkaTemplate
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class PixKafkaSendMessageTest {

    @Mock
    private lateinit var template: KafkaTemplate<String, PixMessage>

    @InjectMocks
    private lateinit var sendMessage: PixKafkaSendMessage

    @Test
    fun shouldSendToPixNewOrder() {
        val pix = PixMessage(
            "id", "123", "321", BigDecimal("1.99"),
            LocalDateTime.now(), PixMessageStatus.PENDING
        )

        sendMessage.send(pix)

        verify(template).send("PIX_NEW_ORDER", pix.id, pix)
    }

    @Test
    fun shouldSendToPixFinished() {
        val pix = PixMessage(
            "id", "123", "321", BigDecimal("1.99"),
            LocalDateTime.now(), PixMessageStatus.FINISHED
        )

        sendMessage.send(pix)

        verify(template).send("PIX_FINISHED", pix.id, pix)
    }

    @Test
    fun shouldSendToPixOrderError() {
        val pix = PixMessage(
            "id", "123", "321", BigDecimal("1.99"),
            LocalDateTime.now(), PixMessageStatus.ERROR
        )

        sendMessage.send(pix)

        verify(template).send("PIX_ORDER_ERROR", pix.id, pix)
    }

    @Test
    fun shouldThrowIfSendMessageThrows() {
        val pix = PixMessage(
            "id", "123", "321", BigDecimal("1.99"),
            LocalDateTime.now(), PixMessageStatus.PENDING
        )

        given(template.send(any(), any(), any()))
            .willThrow(RuntimeException())

        assertThrows<RuntimeException> { sendMessage.send(pix) }
    }
}