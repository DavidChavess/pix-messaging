package com.davidchaves.pixproducerapi.infra

import com.davidchaves.pixproducerapi.data.protocols.CreatePix
import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.data.protocols.PixStatus
import com.davidchaves.pixproducerapi.infra.postgres.PixEntity
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository

class CreatePixPostgresRepository(private val pixRepository: PixRepository) : CreatePixRepository {

    override fun add(pix: CreatePix): CreatePix {
        return pix
            .run {
                PixEntity(
                    identifier = id,
                    targetKey = targetKey,
                    sourceKey = sourceKey,
                    value = value,
                    transferDate = transferDate,
                    status = status.name
                )
            }
            .run(pixRepository::save)
            .run { CreatePix(identifier, targetKey, sourceKey, value, transferDate, PixStatus.valueOf(status)) }
    }
}