package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.*
import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.model.Status
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.domain.usecase.PixDto

class DbCreatePixUseCase(
        private val createPixRepository: CreatePixRepository,
        private val pixSendMessage: PixSendMessage
) : CreatePixUseCase {

    override fun create(pixDto: PixDto) =
            createPixRepository.add(createPix(pixDto))
                    .run { pixMessage(pixDto) }
                    .run(pixSendMessage::send)
                    .run { Pix(id, targetKey, sourceKey, value, transferDate, Status.valueOf(status.name)) }

    private fun pixMessage(pixDto: PixDto) = PixMessage(
            pixDto.id,
            pixDto.targetKey,
            pixDto.sourceKey,
            pixDto.value,
            pixDto.transferDate,
            PixMessageStatus.PENDING
    )

    private fun createPix(pixDto: PixDto) = CreatePix(
            pixDto.id,
            pixDto.targetKey,
            pixDto.sourceKey,
            pixDto.value,
            pixDto.transferDate,
            PixStatus.valueOf(pixDto.status.name)
    )
}