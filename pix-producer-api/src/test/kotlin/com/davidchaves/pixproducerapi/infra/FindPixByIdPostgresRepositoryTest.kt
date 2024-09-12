package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.domain.exception.PixNotFound
import com.davidchaves.pixproducerapi.infra.postgres.jpa.PixModel
import com.davidchaves.pixproducerapi.infra.postgres.jpa.PixRepository
import com.davidchaves.pixproducerapi.infra.repository.PixPostgresRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime.now
import java.util.*

@ExtendWith(MockitoExtension::class)
class FindPixByIdPostgresRepositoryTest {

    @Mock
    private lateinit var pixRepository: PixRepository

    @InjectMocks
    private lateinit var pixIdPostgresRepository: PixPostgresRepository

    @Test
    fun shouldCallPixRepositoryOnSuccess() {
        val transferDate = now()
        val id = UUID.randomUUID()
        val pixModel = PixModel(id, "123", "321", BigDecimal("1.99"), transferDate, "PENDING")
        given(pixRepository.findById(id)).willReturn(Optional.of(pixModel))
        val output = pixIdPostgresRepository.findById(id.toString())
        assertEquals(pixModel, output)
    }

    @Test
    fun shouldThrowPixNotFound() {
        val uuid = UUID.randomUUID()
        given(pixRepository.findById(uuid)).willReturn(Optional.empty())
        assertThrows<PixNotFound> { pixIdPostgresRepository.findById(uuid.toString()) }
    }
}