package com.davidchaves.pixproducerapi.infra.postgres.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PixRepository : JpaRepository<PixModel, UUID>
