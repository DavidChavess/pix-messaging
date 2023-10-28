package com.davidchaves.pixproducerapi.main.presentation.controller

import com.davidchaves.pixproducerapi.domain.model.Pix
import com.davidchaves.pixproducerapi.domain.usecase.CreatePixUseCase
import com.davidchaves.pixproducerapi.domain.usecase.FindPixByIdUseCase
import com.davidchaves.pixproducerapi.domain.usecase.KeyValidatorUseCase
import com.davidchaves.pixproducerapi.domain.usecase.PixDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pix")
class PixRestController(
    private val createPixUseCase: CreatePixUseCase,
    private val findPixByIdUseCase: FindPixByIdUseCase,
    private val keyValidatorUseCase: KeyValidatorUseCase
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody pixDto: PixDto): Pix {
        println("PixRestController, POST: $pixDto");
        return createPixUseCase.create(pixDto);
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Pix {
        println("PixRestController, GET: $id");
        return findPixByIdUseCase.findById(id)
    }

    @PostMapping("/valid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun valid(@RequestParam sourceKey: String, @RequestParam targetKey: String): Map<String, Boolean> {
        println("PixRestController, VALID-POST, sourceKey = $sourceKey, targetKey = $targetKey");
        val isValid = keyValidatorUseCase.valid(sourceKey, targetKey)
        return mapOf(Pair("isValid", isValid))
    }
}