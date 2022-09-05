package com.alexgutjahr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Shorty

fun main(args: Array<String>) {
    runApplication<Shorty>(*args)
}
