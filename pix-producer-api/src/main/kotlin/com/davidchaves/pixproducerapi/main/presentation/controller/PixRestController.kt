package com.davidchaves.pixproducerapi.main.presentation.controller

import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.domain.usecase.FindPixByIdUseCase
import com.davidchaves.pixproducerapi.domain.usecase.PixDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pix")
class PixRestController(
        private val createPixUseCase: CreatePixUseCase,
        private val findPixByIdUseCase: FindPixByIdUseCase,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody pixDto: PixDto) = createPixUseCase.create(pixDto)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String) = findPixByIdUseCase.findById(id)
}