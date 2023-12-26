package com.davidchaves.pixconsumerapi.main.streams.serialization

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

class PixSerdes<T> : Serdes.WrapperSerde<T>(JsonSerializer(), JsonDeserializer()) {

    fun serdes(className: Class<T>): Serde<T>? {
        val serializer: JsonSerializer<T> = JsonSerializer()
        val deserializer: JsonDeserializer<T> = JsonDeserializer(className, false)
        return Serdes.serdeFrom(serializer, deserializer)
    }
}
