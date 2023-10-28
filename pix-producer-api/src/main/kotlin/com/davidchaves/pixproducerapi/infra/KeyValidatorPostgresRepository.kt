package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.KeyValidatorRepository
import com.davidchaves.pixproducerapi.infra.postgres.KeyRepository

class KeyValidatorPostgresRepository(
    private val keyRepository: KeyRepository
) : KeyValidatorRepository {

    override fun valid(sourceKey: String, targetKey: String): Boolean =
        isValid(listOf(sourceKey, targetKey))

    private fun isValid(keys: List<String>): Boolean = keys.all { key ->
        keyRepository.findByKey(key)
            .map { if (it.status == "ACTIVE") true else throw RuntimeException("Chave $key inválida, status inativo.") }
            .orElseThrow { RuntimeException("Chave não encontrada") }
    }
}