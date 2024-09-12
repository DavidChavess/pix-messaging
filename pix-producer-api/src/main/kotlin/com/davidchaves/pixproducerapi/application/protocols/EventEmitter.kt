package com.davidchaves.pixproducerapi.application.protocols

interface EventEmitter<Message> {
    fun emit(queue: String, message: Message)
}
