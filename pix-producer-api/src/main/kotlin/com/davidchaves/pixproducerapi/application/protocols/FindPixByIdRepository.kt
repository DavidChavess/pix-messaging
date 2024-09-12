package com.davidchaves.pixproducerapi.application.protocols

import com.davidchaves.pixproducerapi.domain.entity.Pix

interface FindPixByIdRepository {
    fun findById(id: String): Pix
}
