package com.davidchaves.pixproducerapi.infra.postgres

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PixRepository : JpaRepository<PixEntity, Long> {
    fun findByIdentifier(id: String): Optional<PixEntity>
}
