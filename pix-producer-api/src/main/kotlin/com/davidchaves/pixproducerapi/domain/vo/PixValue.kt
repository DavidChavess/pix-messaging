package com.davidchaves.pixproducerapi.domain.vo

import com.davidchaves.pixproducerapi.domain.exception.PixValueNotPermittedException
import java.math.BigDecimal

class PixValue(
    val value: BigDecimal
){
    init {
        if (value == BigDecimal.ZERO) {
            throw PixValueNotPermittedException(value)
        }
    }
}