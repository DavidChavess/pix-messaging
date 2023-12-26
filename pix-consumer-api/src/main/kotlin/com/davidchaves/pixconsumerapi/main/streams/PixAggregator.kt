package com.davidchaves.pixconsumerapi.main.streams

import java.math.BigDecimal
import java.math.RoundingMode

data class PixAggregator(var count: Int, var total: BigDecimal) {
    private val avg: BigDecimal
        get() = if (count > 0) total.divide(BigDecimal(count), RoundingMode.HALF_DOWN) else BigDecimal.ZERO

    override fun toString(): String {
        return "PixAggregator(count=$count, total=$total, avg=$avg)"
    }
}
