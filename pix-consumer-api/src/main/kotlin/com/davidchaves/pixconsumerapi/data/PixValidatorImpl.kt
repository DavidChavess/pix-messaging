package com.davidchaves.pixconsumerapi.data

import com.davidchaves.pixconsumerapi.data.protocols.KeyValidatorClient
import com.davidchaves.pixconsumerapi.data.protocols.PixDto
import com.davidchaves.pixconsumerapi.data.protocols.UpdatePixClient
import com.davidchaves.pixconsumerapi.data.protocols.UpdatePixStatus
import com.davidchaves.pixconsumerapi.domain.PixMessage
import com.davidchaves.pixconsumerapi.domain.PixValidator
import com.davidchaves.pixconsumerapi.exceptions.PixClientException
import kotlin.random.Random

class PixValidatorImpl(
    private val keyValidatorClient: KeyValidatorClient,
    private val updatePixClient: UpdatePixClient
) : PixValidator {

     override fun process(pixMessage: PixMessage) {
        println("Verificando se a chave pix é valida, sourceKey = ${pixMessage.sourceKey}, targetKey = ${pixMessage.targetKey}")
        try {
            keyValidatorClient.valid(pixMessage.sourceKey, pixMessage.targetKey)
            println("Chave pix é valida, realizando pix...")
            updatePixClient.update(toUpdatePix(pixMessage, UpdatePixStatus.FINISHED))
            Thread.sleep(Random.nextLong(1, 20))
        } catch (ex: PixClientException) {
            println("Não foi possivel realizar pix, mudando status para ERROR para ser inserido na fila de erros")
            updatePixClient.update(toUpdatePix(pixMessage, UpdatePixStatus.ERROR))
            ex.printStackTrace()
            throw ex
        }
    }

    private fun toUpdatePix(pixMessage: PixMessage, status: UpdatePixStatus) = PixDto(
        pixMessage.id,
        pixMessage.targetKey,
        pixMessage.sourceKey,
        pixMessage.value,
        status,
    )
}