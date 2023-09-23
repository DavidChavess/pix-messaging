package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.infra.postgres.PixEntity
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class FindPixByIdPostgresRepositoryTest {

    @Mock
    private lateinit var pixRepository: PixRepository

    @InjectMocks
    private lateinit var findPixIdPostgresRepository: FindPixIdPostgresRepository

    @Test
    fun shouldCallPixRepositoryOnSuccess() {
        val localDateTime = LocalDateTime.now()

        val pixEntity =
                PixEntity(1, "id", "123", "321", BigDecimal("1.99"), localDateTime, "PENDING")

        given(pixRepository.findByIdentifier("any")).willReturn(Optional.of(pixEntity))

        val result = findPixIdPostgresRepository.findById("any")

        assertEquals(result, result)
        verify(pixRepository).findByIdentifier("any")
    }

    @Test
    fun shouldThrowPixNotFound() {
        given(pixRepository.findByIdentifier("any")).willReturn(Optional.empty())
        assertThrows<RuntimeException> { findPixIdPostgresRepository.findById("any") }
    }
}