package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.data.protocols.KeyValidatorRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IndexOutOfBoundsException

@ExtendWith(MockitoExtension::class)
class DbKeyValidatorUseCaseTest {

    @Mock
    private lateinit var keyValidatorRepository: KeyValidatorRepository

    @InjectMocks
    private lateinit var dbKeyValidatorUseCase: DbKeyValidatorUseCase

    @Test
    fun shouldCallKeyValidatorRepoWithCorrectValues() {
        val sourceKey = "any_source_key"
        val targetKey = "any_target_key"
        dbKeyValidatorUseCase.valid(sourceKey, targetKey)
        verify(keyValidatorRepository).valid(sourceKey, targetKey)
    }

    @Test
    fun shouldThrowIfKeyValidatorRepoThrows() {
        val sourceKey = "any_source_key"
        val targetKey = "any_target_key"

        given(keyValidatorRepository.valid(sourceKey, targetKey))
            .willThrow(IndexOutOfBoundsException())

        assertThrows<IndexOutOfBoundsException> { dbKeyValidatorUseCase.valid(sourceKey, targetKey) }
        verify(keyValidatorRepository).valid(sourceKey, targetKey)
    }
}