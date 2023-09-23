package com.davidchaves.pixproducerapi.domain.usecase

import com.davidchaves.pixproducerapi.domain.model.Pix

interface FindPixByIdUseCase {
    fun findById(id: String): Pix
}