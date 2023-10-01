package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.CreatePix
import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.data.protocols.PixStatus
import com.davidchaves.pixproducerapi.infra.postgres.PixEntity
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository

class CreatePixPostgresRepository(private val pixRepository: PixRepository) : CreatePixRepository {

    override fun add(pix: CreatePix): CreatePix = pixRepository.findByIdentifier(pix.id)
        .map(PixEntity::id).orElse(null)
        .let { toPixEntity(it, pix) }
        .run(pixRepository::save)
        .run { CreatePix(identifier, targetKey, sourceKey, value, transferDate, PixStatus.valueOf(status)) }


    private fun toPixEntity(id: Long?, pix: CreatePix) = PixEntity(
        id,
        identifier = pix.id,
        targetKey = pix.targetKey,
        sourceKey = pix.sourceKey,
        value = pix.value,
        transferDate = pix.transferDate,
        status = pix.status.name
    )
}