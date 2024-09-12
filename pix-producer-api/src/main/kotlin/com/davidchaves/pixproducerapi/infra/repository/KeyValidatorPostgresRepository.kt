package com.davidchaves.pixproducerapi.infra.repository

import com.davidchaves.pixproducerapi.application.protocols.KeyValidatorRepository
import com.davidchaves.pixproducerapi.infra.postgres.jpa.KeyRepository

class KeyValidatorPostgresRepository(
    private val keyRepository: KeyRepository
) : KeyValidatorRepository {

    override fun valid(sourceKey: String, targetKey: String): Boolean {
        return listOf(sourceKey, targetKey)
            .all { key ->
                keyRepository.findByKey(key)
                    .map { if (it.status == "ACTIVE") true else throw RuntimeException("Chave $key inválida, status inativo.") }
                    .orElseThrow { RuntimeException("Chave não encontrada") }
            }
    }
}
