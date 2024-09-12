package com.davidchaves.pixproducerapi.infra.postgres.jpa

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "key")
data class KeyModel(
    @Id
    var id: UUID? = null,
    val key: String,
    val status: String
)
