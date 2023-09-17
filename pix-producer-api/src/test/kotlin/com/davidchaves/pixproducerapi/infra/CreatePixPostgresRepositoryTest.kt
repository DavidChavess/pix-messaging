package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.CreatePix
import com.davidchaves.pixproducerapi.data.protocols.PixStatus
import com.davidchaves.pixproducerapi.infra.postgres.PixEntity
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class CreatePixPostgresRepositoryTest {

    @Mock
    private lateinit var pixRepository: PixRepository

    @InjectMocks
    private lateinit var createPixPostgresRepository: CreatePixPostgresRepository

    @Test
    fun shouldCallPixRepositoryOnSuccess() {
        val localDateTime = LocalDateTime.now()

        val createPix =
            CreatePix("id", "123", "321", BigDecimal("1.99"), localDateTime, PixStatus.PENDING)

        val pixEntity =
            PixEntity(null, "id", "123", "321", BigDecimal("1.99"), localDateTime, "PENDING")

        given(pixRepository.save(pixEntity)).willReturn(pixEntity)

        val result = createPixPostgresRepository.add(createPix)

        assertEquals(createPix, result)
        verify(pixRepository).save(pixEntity);
    }
}