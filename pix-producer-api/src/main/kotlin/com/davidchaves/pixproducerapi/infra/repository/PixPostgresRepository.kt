package com.davidchaves.pixproducerapi.infra.repository

import com.davidchaves.pixproducerapi.application.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.application.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.domain.entity.Pix
import com.davidchaves.pixproducerapi.domain.exception.PixNotFound
import com.davidchaves.pixproducerapi.infra.postgres.jpa.PixModel
import com.davidchaves.pixproducerapi.infra.postgres.jpa.PixRepository
import java.util.UUID

class PixPostgresRepository(private val pixRepository: PixRepository) : CreatePixRepository, FindPixByIdRepository {

    override fun add(pix: Pix) {
        val entity = PixModel(
            id = UUID.fromString(pix.id),
            targetKey = pix.targetKey,
            sourceKey = pix.sourceKey,
            value = pix.value,
            transferDate = pix.transferDate,
            status = pix.status.name
        )
        pixRepository.save(entity)
    }

    override fun findById(id: String): Pix {
        return pixRepository.findById(UUID.fromString(id))
            .map(PixModel::toEntity)
            .orElseThrow { PixNotFound() }
    }
}