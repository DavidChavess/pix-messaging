package com.davidchaves.pixproducerapi.main.config

import com.davidchaves.pixproducerapi.data.DbCreatePixUseCase
import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.data.protocols.PixMessage
import com.davidchaves.pixproducerapi.data.protocols.PixSendMessage
import com.davidchaves.pixproducerapi.data.util.CreatePixMapper
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.infra.CreatePixPostgresRepository
import com.davidchaves.pixproducerapi.infra.PixKafkaSendMessage
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class CreatePixConfig {

    @Bean
    fun createPixRepository(pixRepository: PixRepository): CreatePixRepository =
            CreatePixPostgresRepository(pixRepository)

    @Bean
    fun pixKafkaSendMessage(template: KafkaTemplate<String, PixMessage>): PixSendMessage =
            PixKafkaSendMessage(template)

    @Bean
    fun createPixUseCase(
            createPixRepository: CreatePixRepository,
            pixSendMessage: PixSendMessage
    ): CreatePixUseCase =
            DbCreatePixUseCase(createPixRepository, pixSendMessage, Mappers.getMapper(CreatePixMapper::class.java))
}