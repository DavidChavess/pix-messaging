package com.davidchaves.pixproducerapi.infra.postgres

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface KeyRepository : JpaRepository<KeyEntity, Long> {
    fun findByKey(key: String): Optional<KeyEntity>
}