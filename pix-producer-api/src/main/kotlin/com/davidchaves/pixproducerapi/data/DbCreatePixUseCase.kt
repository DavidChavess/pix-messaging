package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.data.util.CreatePixMapper
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.domain.usecase.PixDto

class DbCreatePixUseCase(
        private val createPixRepository: CreatePixRepository,
        private val createPixMapper: CreatePixMapper
) : CreatePixUseCase {

    override fun create(pixDto: PixDto) =
        createPixMapper.pixDtoToCreatePix(pixDto)
            .apply { println("Salvando pix no banco, pix = $pixDto") }
            .run(createPixRepository::add)
            .run(createPixMapper::createPixToPix)
}