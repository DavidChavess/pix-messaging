package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.*
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import com.davidchaves.pixproducerapi.domain.usecase.PixDto
import com.davidchaves.pixproducerapi.domain.usecase.PixDtoStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoInteractions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class DbCreatePixUseCaseTest {

    @Mock
    private lateinit var createPixRepository: CreatePixRepository

    @Mock
    private lateinit var pixSendMessage: PixSendMessage

    @InjectMocks
    private lateinit var dbCreatePix: DbCreatePixUseCase

    @Test
    fun shouldThrowIfPixRepositoryThrows() {
        val localDateTime = LocalDateTime.now()

        val pixDto =
                PixDto("id", "123", "321", BigDecimal("1.99"), localDateTime, PixDtoStatus.PENDING)

        val createPix =
                CreatePix("id", "123", "321", BigDecimal("1.99"), localDateTime, PixStatus.PENDING)

        given(createPixRepository.add(createPix)).willReturn(createPix)
        given(createPixRepository.add(createPix)).willThrow(RuntimeException())

        assertThrows<RuntimeException> { dbCreatePix.create(pixDto) }

        verifyNoInteractions(pixSendMessage);
    }

    @Test
    fun shouldThrowIfPixMessageThrows() {
        val localDateTime = LocalDateTime.now()

        val pixDto =
                PixDto("id", "123", "321", BigDecimal("1.99"), localDateTime, PixDtoStatus.PENDING)

        val message =
                PixMessage("id", "123", "321", BigDecimal("1.99"), localDateTime, PixMessageStatus.PENDING)

        given(pixSendMessage.send(message)).willThrow(RuntimeException())

        assertThrows<RuntimeException> { dbCreatePix.create(pixDto) }
    }

    @Test
    fun shouldCallCreatePixAndSendMessageOnSuccess() {
        val localDateTime = LocalDateTime.now()

        val pixDto =
                PixDto("id", "123", "321", BigDecimal("1.99"), localDateTime, PixDtoStatus.PENDING)

        val pix = Pix("id", "123", "321", BigDecimal("1.99"), localDateTime, Status.PENDING)

        val createPix =
                CreatePix("id", "123", "321", BigDecimal("1.99"), localDateTime, PixStatus.PENDING)

        val message =
                PixMessage("id", "123", "321", BigDecimal("1.99"), localDateTime, PixMessageStatus.PENDING)

        given(createPixRepository.add(createPix)).willReturn(createPix)
        given(pixSendMessage.send(message)).willReturn(message)

        val result = dbCreatePix.create(pixDto)

        assertEquals(pix, result)
        verify(createPixRepository).add(createPix);
        verify(pixSendMessage).send(message);
    }
}