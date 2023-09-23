package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.data.protocols.PixById
import com.davidchaves.pixproducerapi.data.protocols.PixByIdStatus
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository

class FindPixIdPostgresRepository(private val pixRepository: PixRepository) : FindPixByIdRepository {

    override fun findById(id: String): PixById {
        return pixRepository.findByIdentifier(id)
                .map {
                    PixById(
                            it.identifier,
                            it.targetKey,
                            it.sourceKey,
                            it.value,
                            it.transferDate,
                            PixByIdStatus.valueOf(it.status)
                    )
                }
                .orElseThrow { RuntimeException("Pix not found") }
    }
}