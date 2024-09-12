package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.protocols.KeyValidatorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DbKeyValidatorUseCaseTest {

    @Mock
    private lateinit var keyValidatorRepository: KeyValidatorRepository

    @InjectMocks
    private lateinit var dbKeyValidatorUseCase: DbKeyValidatorUseCase

    @Test
    fun shouldCallKeyValidatorRepoWithCorrectValues() {
        val input = DbKeyValidatorUseCase.Input("any_source_key", "any_target_key")
        val output = dbKeyValidatorUseCase.execute(input)
        verify(keyValidatorRepository).valid(input.sourceKey, input.targetKey)
        Assertions.assertTrue(output.isValid)
    }

    @Test
    fun shouldThrowIfKeyValidatorRepoThrows() {
        val input = DbKeyValidatorUseCase.Input("any_source_key", "any_target_key")
        given(keyValidatorRepository.valid(input.sourceKey, input.targetKey)).willThrow(IndexOutOfBoundsException())
        assertThrows<IndexOutOfBoundsException> { dbKeyValidatorUseCase.execute(input) }
    }
}