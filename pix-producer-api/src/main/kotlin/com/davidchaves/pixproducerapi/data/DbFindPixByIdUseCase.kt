package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import com.davidchaves.pixproducerapi.domain.usecase.FindPixByIdUseCase

class DbFindPixByIdUseCase(
    private val findPixByIdRepository: FindPixByIdRepository
) : FindPixByIdUseCase {

    override fun findById(id: String): Pix = findPixByIdRepository
        .findById(id)
        .let { Pix(it.id, it.targetKey, it.sourceKey, it.value, it.transferDate, Status.valueOf(it.status.name)) }
}