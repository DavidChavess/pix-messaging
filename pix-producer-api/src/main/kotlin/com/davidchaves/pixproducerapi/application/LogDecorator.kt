package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.protocols.Logger
import com.davidchaves.pixproducerapi.application.protocols.UseCase

class LogDecorator<Input : Any, Output : Any>(
    private val useCase: UseCase<Input, Output>
) : UseCase<Input, Output> {

    private val log = Logger.create(useCase::class)

    override fun execute(input: Input): Output {
        log.info {
            message = "Iniciando usecase"
            withParam("input", input)
        }
        try {
            val output = useCase.execute(input)
            log.info {
                message = "Finalizado usecase"
                withParam("output", output)
            }
            return output
        } catch (ex: Exception) {
            log.error {
                message = "Erro no usecase"
                withParam("error", ex)
            }
            throw ex
        }
    }
}