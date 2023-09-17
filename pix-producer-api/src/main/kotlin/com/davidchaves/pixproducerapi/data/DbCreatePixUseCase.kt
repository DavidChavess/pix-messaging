package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.CreatePix
import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.data.protocols.PixStatus
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.domain.usecase.PixDto

class DbCreatePixUseCase(private val createPixRepository: CreatePixRepository) : CreatePixUseCase {

    override fun create(pixDto: PixDto): Pix {
        val createPix = CreatePix(
            pixDto.id,
            pixDto.targetKey,
            pixDto.sourceKey,
            pixDto.value,
            pixDto.transferDate,
            PixStatus.valueOf(pixDto.status.name)
        )
        return createPixRepository.add(createPix)
            .run { Pix(id, targetKey, sourceKey, value, transferDate, Status.valueOf(status.name)) }
    }
}