package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.*
import com.davidchaves.pixproducerapi.data.util.CreatePixMapper
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import com.davidchaves.pixproducerapi.domain.usecase.PixDto
import com.davidchaves.pixproducerapi.domain.usecase.PixDtoStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class DbCreatePixUseCaseTest {

    @Mock
    private lateinit var createPixRepository: CreatePixRepository

    @Mock
    private lateinit var createPixMapper: CreatePixMapper

    @InjectMocks
    private lateinit var dbCreatePix: DbCreatePixUseCase

    private val localDateTime = LocalDateTime.now()

    private val pixDto =
        PixDto("id", "123", "321", BigDecimal("1.99"), localDateTime, PixDtoStatus.PENDING)

    private val pix =
        Pix("id", "123", "321", BigDecimal("1.99"), localDateTime, Status.PENDING)

    private val createPix =
        CreatePix("id", "123", "321", BigDecimal("1.99"), localDateTime, PixStatus.PENDING)


    @Test
    fun shouldThrowIfCreatePixThrows() {
        given(createPixMapper.pixDtoToCreatePix(pixDto)).willReturn(createPix)
        given(createPixRepository.add(createPix)).willThrow(IndexOutOfBoundsException())
        assertThrows<IndexOutOfBoundsException> { dbCreatePix.create(pixDto) }
    }

    @Test
    fun shouldCallCreatePixAndSendMessageOnSuccess() {
        given(createPixMapper.pixDtoToCreatePix(pixDto)).willReturn(createPix)
        given(createPixMapper.createPixToPix(createPix)).willReturn(pix)
        given(createPixRepository.add(createPix)).willReturn(createPix)

        assertEquals(pix, dbCreatePix.create(pixDto))

        verify(createPixRepository).add(createPix);
        verify(createPixMapper).pixDtoToCreatePix(pixDto)
        verify(createPixMapper).createPixToPix(createPix)
    }
}