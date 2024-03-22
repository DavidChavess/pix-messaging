package com.davidchaves.pixconsumerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class PixConsumerApiApplication

fun main(args: Array<String>) {
    runApplication<PixConsumerApiApplication>(*args)
}
