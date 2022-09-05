package com.alexgutjahr

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class SpringCrudApiApplication {

    @Bean
    fun run(repo: UserRepository) = CommandLineRunner {
        for (count in 1..10) {
            repo.save(User("${UUID.randomUUID()}@alexgutjahr.com").apply {
                this.name = "Anonymous"
            })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringCrudApiApplication>(*args)
}
