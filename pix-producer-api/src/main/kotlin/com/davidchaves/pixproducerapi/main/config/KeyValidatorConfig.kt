package com.davidchaves.pixproducerapi.main.config

import com.davidchaves.pixproducerapi.data.DbKeyValidatorUseCase
import com.davidchaves.pixproducerapi.data.protocols.KeyValidatorRepository
import com.davidchaves.pixproducerapi.domain.usecase.KeyValidatorUseCase
import com.davidchaves.pixproducerapi.infra.KeyValidatorPostgresRepository
import com.davidchaves.pixproducerapi.infra.postgres.KeyRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeyValidatorConfig {

    @Bean
    fun keyValidatorRepository(keyRepository: KeyRepository): KeyValidatorRepository =
        KeyValidatorPostgresRepository(keyRepository)

    @Bean
    fun keyValidatorUseCase(keyValidatorRepository: KeyValidatorRepository): KeyValidatorUseCase =
        DbKeyValidatorUseCase(keyValidatorRepository)
}