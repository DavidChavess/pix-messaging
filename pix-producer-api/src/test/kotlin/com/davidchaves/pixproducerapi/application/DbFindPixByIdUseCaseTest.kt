package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.domain.entity.Pix
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
import java.util.*

@ExtendWith(MockitoExtension::class)
class DbFindPixByIdUseCaseTest {

    @Mock
    private lateinit var findPixByIdRepository: FindPixByIdRepository

    @InjectMocks
    private lateinit var dbFindPixByIdUseCase: DbFindPixByIdUseCase

    @Test
    fun shouldThrowIfFindByIdPixThrows() {
        given(findPixByIdRepository.findById("any")).willThrow(IndexOutOfBoundsException())
        assertThrows<IndexOutOfBoundsException> { dbFindPixByIdUseCase.execute(UUID.randomUUID().toString()) }
    }

    @Test
    fun shouldGetPixById() {
        val localDateTime = LocalDateTime.now()
        val uuid = UUID.randomUUID().toString()
        val pix = Pix(uuid, "123", "321", BigDecimal("1.99"), localDateTime, Pix.Status.PENDING)
        given(findPixByIdRepository.findById(uuid)).willReturn(pix)
        val result = dbFindPixByIdUseCase.execute(uuid)
        assertEquals(result, pix)
        verify(findPixByIdRepository).findById(uuid)
    }

}