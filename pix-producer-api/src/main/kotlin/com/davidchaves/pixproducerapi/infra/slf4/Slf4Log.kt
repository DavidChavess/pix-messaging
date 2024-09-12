package com.davidchaves.pixproducerapi.infra.slf4

import com.davidchaves.pixproducerapi.application.protocols.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import kotlin.reflect.KClass

class Slf4Log(kClass: KClass<*>) : Logger {
    private val log = LoggerFactory.getLogger(kClass.java)

    override fun info(contextFunction: Logger.Context.() -> Unit) {
        if (log.isInfoEnabled) {
            val context = Logger.Context()
            contextFunction(context)
            context.params.forEach {
                MDC.put(it.key, it.value.toString())
            }
            log.info(context.message)
            context.params.forEach {
                MDC.remove(it.key)
            }
        }
    }

    override fun error(contextFunction: Logger.Context.() -> Unit) {
        if (log.isErrorEnabled) {
            val context = Logger.Context()
            contextFunction(context)
            context.params.forEach {
                MDC.put(it.key, it.value.toString())
            }
            log.error(context.message)
            context.params.forEach {
                MDC.remove(it.key)
            }
        }
    }
}