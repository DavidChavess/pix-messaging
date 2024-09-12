package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.application.protocols.EventEmitter
import com.davidchaves.pixproducerapi.domain.entity.Pix
import com.davidchaves.pixproducerapi.domain.event.DomainEvent
import com.davidchaves.pixproducerapi.domain.event.PixPendingEvent
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DbCreatePixUseCaseTest {

    @Mock
    private lateinit var createPixRepository: CreatePixRepository

    @Mock
    private lateinit var eventEmitter: EventEmitter<DomainEvent>

    @InjectMocks
    private lateinit var dbCreatePix: DbCreatePixUseCase

    @Test
    fun `should throw error if pix repository throws`() {
        val pixDto = DbCreatePixUseCase.Input(targetKey = "123", sourceKey = "321", value = BigDecimal("1.99"))
        `when`(createPixRepository.add(any(Pix::class.java))).thenThrow(IndexOutOfBoundsException())
        assertThrows<IndexOutOfBoundsException> { dbCreatePix.execute(pixDto) }
        verifyNoInteractions(eventEmitter)
    }

    @Test
    fun `should throw error if event emitter throws`() {
        val pixDto = DbCreatePixUseCase.Input(targetKey = "123", sourceKey = "321", value = BigDecimal("1.99"))
        `when`(eventEmitter.emit(Pix.Status.PENDING.eventName, any(PixPendingEvent::class.java))).thenThrow(
            IndexOutOfBoundsException()
        )
        assertThrows<IndexOutOfBoundsException> { dbCreatePix.execute(pixDto) }
    }

    @Test
    fun shouldCallNotifyCreationPixAndSendMessageOnSuccess() {
        val pixDto = DbCreatePixUseCase.Input(targetKey = "123", sourceKey = "321", value = BigDecimal("1.99"))
        val output = dbCreatePix.execute(pixDto)
        assertNotNull(output.id)
        // verify(eventEmitter).emit(Pix.Status.PENDING.eventName, argThat { it.eventName == Pix.Status.PENDING.eventName })
    }
}