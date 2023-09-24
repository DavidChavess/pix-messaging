package com.davidchaves.pixproducerapi.data.util

import com.davidchaves.pixproducerapi.data.protocols.CreatePix
import com.davidchaves.pixproducerapi.data.protocols.PixMessage
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.usecase.PixDto
import org.mapstruct.Mapper

@Mapper
interface CreatePixMapper {
    fun pixDtoToCreatePix(pixDto: PixDto): CreatePix
    fun pixDtoToPixMessage(pixDto: PixDto): PixMessage
    fun pixMessageToPix(pixMessage: PixMessage): Pix
}