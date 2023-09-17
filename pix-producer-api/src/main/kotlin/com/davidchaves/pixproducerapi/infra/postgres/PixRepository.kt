package com.davidchaves.pixproducerapi.infra.postgres

import org.springframework.data.jpa.repository.JpaRepository

interface PixRepository : JpaRepository<PixEntity, Long> {

}
