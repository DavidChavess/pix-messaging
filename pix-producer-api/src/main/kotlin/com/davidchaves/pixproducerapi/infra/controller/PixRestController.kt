package com.davidchaves.pixproducerapi.infra.controller

import com.davidchaves.pixproducerapi.application.DbCreatePixUseCase
import com.davidchaves.pixproducerapi.application.DbFindPixByIdUseCase
import com.davidchaves.pixproducerapi.application.DbKeyValidatorUseCase
import com.davidchaves.pixproducerapi.application.LogDecorator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("pix")
class PixRestController(
    private val createPixUseCase: DbCreatePixUseCase,
    private val findPixByIdUseCase: DbFindPixByIdUseCase,
    private val keyValidatorUseCase: DbKeyValidatorUseCase
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody pixDto: DbCreatePixUseCase.Input): DbCreatePixUseCase.Output {
        return LogDecorator(createPixUseCase).execute(pixDto)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): DbFindPixByIdUseCase.Output {
        return LogDecorator(findPixByIdUseCase).execute(id)
    }

    @PostMapping("/valid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun valid(@RequestParam sourceKey: String, @RequestParam targetKey: String): DbKeyValidatorUseCase.Output {
        val input = DbKeyValidatorUseCase.Input(targetKey = targetKey, sourceKey = sourceKey)
        return LogDecorator(keyValidatorUseCase).execute(input)
    }
}