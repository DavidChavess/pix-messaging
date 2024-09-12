package com.davidchaves.pixproducerapi.domain.exception

import java.math.BigDecimal

class PixValueNotPermittedException(val value: BigDecimal)
    : RuntimeException("Este valor de pix não é permitido, valor: $value")
