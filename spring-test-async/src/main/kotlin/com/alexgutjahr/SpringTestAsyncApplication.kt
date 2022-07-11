package com.alexgutjahr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTestAsyncApplication

fun main(args: Array<String>) {
    runApplication<SpringTestAsyncApplication>(*args)
}
