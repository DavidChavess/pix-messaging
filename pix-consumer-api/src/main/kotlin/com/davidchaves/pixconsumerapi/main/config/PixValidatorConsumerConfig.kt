package com.davidchaves.pixconsumerapi.main.config

import com.davidchaves.pixconsumerapi.data.PixValidatorImpl
import com.davidchaves.pixconsumerapi.data.protocols.PixClient
import com.davidchaves.pixconsumerapi.domain.PixMessage
import com.davidchaves.pixconsumerapi.domain.PixValidator
import com.davidchaves.pixconsumerapi.infra.PixClientImpl
import com.davidchaves.pixconsumerapi.infra.PixRetrofitClient
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class PixValidatorConsumerConfig {

    @Value("\${spring.kafka.bootstrap-servers:localhost:9092}")
    private lateinit var bootstrapServers: String

    @Value("\${pix.client.url:http://localhost:8080}")
    private lateinit var pixClientUrl: String

    @Bean
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(pixClientUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    @Bean
    fun pixRetrofitClient(retrofit: Retrofit): PixRetrofitClient =
        retrofit.create(PixRetrofitClient::class.java)

    @Bean
    fun pixClient(pixRetrofitClient: PixRetrofitClient): PixClient =
        PixClientImpl(pixRetrofitClient)

    @Bean
    fun pixValidator(pixClient: PixClient): PixValidator =
        PixValidatorImpl(pixClient, pixClient)

    @Bean
    fun consumerFactory(): ConsumerFactory<String, PixMessage> {
        val props = mutableMapOf<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java.name
        props[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false

        return DefaultKafkaConsumerFactory(props, StringDeserializer(), JsonDeserializer(PixMessage::class.java, false))
    }

    @Bean
    fun listenerContainerFactory(consumerFactory: ConsumerFactory<String, PixMessage>):
            KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PixMessage>> {
        val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, PixMessage>()
        // listenerContainerFactory.setConcurrency(3)
        listenerContainerFactory.consumerFactory = consumerFactory
        listenerContainerFactory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        return listenerContainerFactory
    }
}