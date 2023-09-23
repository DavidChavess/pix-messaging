package com.davidchaves.pixproducerapi.main.config

import com.davidchaves.pixproducerapi.data.DbCreatePixUseCase
import com.davidchaves.pixproducerapi.data.protocols.CreatePixRepository
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.infra.CreatePixPostgresRepository
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CreatePixConfig {

    @Bean
    fun createPixRepository(pixRepository: PixRepository): CreatePixRepository =
            CreatePixPostgresRepository(pixRepository)

    @Bean
    fun createPixUseCase(createPixRepository: CreatePixRepository): CreatePixUseCase =
            DbCreatePixUseCase(createPixRepository)
}