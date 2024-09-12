package com.davidchaves.pixproducerapi.domain.vo

import com.davidchaves.pixproducerapi.domain.exception.PixValueNotPermittedException
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PixValueTest {
    @Test
    fun `should create a pix value`() {
        val pixValue = PixValue(BigDecimal("3.99"))
        assertEquals(pixValue.value, BigDecimal("3.99"))
    }

    @Test
    fun `should throw if value is zero`() {
        assertThrows<PixValueNotPermittedException> { PixValue(BigDecimal.ZERO) }
    }
}