package com.davidchaves.pixproducerapi.application.protocols

interface UseCase<Input: Any, Output: Any> {
    fun execute(input: Input): Output
}