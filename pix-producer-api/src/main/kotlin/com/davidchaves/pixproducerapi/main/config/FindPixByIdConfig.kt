package com.davidchaves.pixproducerapi.main.config

import com.davidchaves.pixproducerapi.data.DbFindPixByIdUseCase
import com.davidchaves.pixproducerapi.data.protocols.FindPixByIdRepository
import com.davidchaves.pixproducerapi.domain.usecase.FindPixByIdUseCase
import com.davidchaves.pixproducerapi.infra.FindPixIdPostgresRepository
import com.davidchaves.pixproducerapi.infra.postgres.PixRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindPixByIdConfig {

    @Bean
    fun findPixByIdRepository(pixRepository: PixRepository): FindPixByIdRepository =
        FindPixIdPostgresRepository(pixRepository)

    @Bean
    fun findPixByIdUseCase(findPixByIdRepository: FindPixByIdRepository): FindPixByIdUseCase =
        DbFindPixByIdUseCase(findPixByIdRepository)
}