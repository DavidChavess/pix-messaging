package com.davidchaves.pixproducerapi.application.protocols

import com.davidchaves.pixproducerapi.infra.slf4.Slf4Log
import kotlin.reflect.KClass

interface Logger {
    fun info(contextFunction: Context.() -> Unit)
    fun error(contextFunction: Context.() -> Unit)

    class Context {
        lateinit var message: String
        val params: MutableMap<String, Any> = mutableMapOf()

        fun withParam(key: String, value: Any) {
            params[key] = value
        }
    }

    companion object {
        fun create(kClass: KClass<*>): Logger {
            return Slf4Log(kClass)
        }
    }
}