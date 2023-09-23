package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.*
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import org.junit.jupiter.api.Assertions.*
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
class DbFindPixByIdUseCaseTest {

    @Mock
    private lateinit var findPixByIdRepository: FindPixByIdRepository

    @InjectMocks
    private lateinit var dbFindPixByIdUseCase: DbFindPixByIdUseCase

    @Test
    fun shouldThrowIfFindByIdPixThrows() {
        given(findPixByIdRepository.findById("any")).willThrow(RuntimeException())
        assertThrows<RuntimeException> { dbFindPixByIdUseCase.findById("any") }
    }

    @Test
    fun shouldGetPixById() {
        val localDateTime = LocalDateTime.now()

        val pixById =
            PixById("id", "123", "321", BigDecimal("1.99"), localDateTime, PixByIdStatus.PENDING)

        given(findPixByIdRepository.findById("any")).willReturn(pixById)

        val pix =
            Pix("id", "123", "321", BigDecimal("1.99"), localDateTime, Status.PENDING)

        val result = dbFindPixByIdUseCase.findById("any")

        assertEquals(result, pix)
        verify(findPixByIdRepository).findById("any")
    }

}