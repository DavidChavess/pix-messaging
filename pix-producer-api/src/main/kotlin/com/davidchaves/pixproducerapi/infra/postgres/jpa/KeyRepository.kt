package com.davidchaves.pixproducerapi.infra.postgres.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface KeyRepository : JpaRepository<KeyModel, UUID> {
    fun findByKey(key: String): Optional<KeyModel>
}