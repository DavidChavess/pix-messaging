package com.davidchaves.pixproducerapi.domain.event

open class Aggregate {

    private val events = mutableMapOf<String, (event: DomainEvent) -> Unit>()

    fun register(queue: String, function: (event: DomainEvent) -> Unit) {
        events[queue] = function
    }

    fun notify(queue: String, event: DomainEvent) {
       events[queue]?.invoke(event)
    }
}
