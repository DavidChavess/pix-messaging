package com.davidchaves.pixproducerapi.application.protocols

import com.davidchaves.pixproducerapi.domain.entity.Pix

interface CreatePixRepository {
    fun add(pix: Pix)
}
