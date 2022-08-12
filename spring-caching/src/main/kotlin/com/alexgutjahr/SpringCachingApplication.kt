package com.alexgutjahr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class SpringCachingApplication

fun main(args: Array<String>) {
    runApplication<SpringCachingApplication>(*args)
}
