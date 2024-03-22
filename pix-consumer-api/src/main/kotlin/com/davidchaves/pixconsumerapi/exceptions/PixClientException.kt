package com.davidchaves.pixconsumerapi.exceptions

data class PixClientException(val code: Int, override val message: String) : Exception()
