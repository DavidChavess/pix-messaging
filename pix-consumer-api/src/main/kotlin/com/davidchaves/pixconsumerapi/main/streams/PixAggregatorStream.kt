package com.davidchaves.pixconsumerapi.main.streams

import com.davidchaves.pixconsumerapi.domain.PixMessage
import com.davidchaves.pixconsumerapi.domain.PixValidator
import com.davidchaves.pixconsumerapi.main.streams.serialization.PixSerdes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.*
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
@ConditionalOnProperty(name = ["kafka-streams.enabled"], havingValue = "true")
class PixAggregatorStream(
    private val pixValidator: PixValidator
) {

    @Bean
    fun process(streams: StreamsBuilder): KStream<String, PixMessage> {
        return streams
            .stream(
                "PIX_NEW_ORDER",
                Consumed.with(Serdes.String(), PixSerdes<PixMessage>().serdes(PixMessage::class.java))
            )
            .filter { _, message -> message.isPending() }
            .peek { _, value -> CoroutineScope(Dispatchers.IO).launch { pixValidator.process(value) } }
    }

    @Bean
    fun aggregate(streams: StreamsBuilder): KStream<String, PixAggregator> {
        val aggregator = streams
            .stream(
                "PIX_NEW_ORDER",
                Consumed.with(Serdes.String(), PixSerdes<PixMessage>().serdes(PixMessage::class.java))
            )
            .filter { _, message -> message.isFinished() }
            .groupBy { _, message -> message.sourceKey }
            .aggregate(
                { PixAggregator(0, BigDecimal.ZERO) },
                { _, pix, aggregate ->
                    aggregate.total += pix.value
                    aggregate.count++
                    aggregate
                },
                Materialized.with(Serdes.String(), PixSerdes<PixAggregator>().serdes(PixAggregator::class.java))
            )
            .toStream()

        aggregator
            .to(
                "PIX_TOPIC_AGGREGATION_AVG",
                Produced.with(Serdes.String(), PixSerdes<PixAggregator>().serdes(PixAggregator::class.java))
            )

        aggregator.print(Printed.toSysOut())
        return aggregator
    }
}

