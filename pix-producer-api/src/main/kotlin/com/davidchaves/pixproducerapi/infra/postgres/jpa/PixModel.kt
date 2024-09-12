package com.davidchaves.pixproducerapi.infra.postgres.jpa

import com.davidchaves.pixproducerapi.domain.entity.Pix
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "pix")
data class PixModel(
    @Id
    var id: UUID? = null,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: String
) {
    companion object {
        fun toEntity(pixModel: PixModel): Pix {
            return Pix(
                id = pixModel.id.toString(),
                targetKey = pixModel.targetKey,
                sourceKey = pixModel.sourceKey,
                value = pixModel.value,
                transferDate = pixModel.transferDate,
                status = Pix.Status.valueOf(pixModel.status)
            )
        }
    }
}
