package com.davidchaves.pixproducerapi.infra.postgres

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pix")
data class PixEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val identifier: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: String
)
