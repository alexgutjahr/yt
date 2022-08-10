package com.alexgutjahr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class SpringEventsApplication

fun main(args: Array<String>) {
    runApplication<SpringEventsApplication>(*args)
}
