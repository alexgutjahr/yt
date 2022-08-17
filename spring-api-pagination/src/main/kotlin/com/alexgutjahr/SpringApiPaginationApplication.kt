package com.alexgutjahr

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class SpringApiPaginationApplication {

    @Bean
    fun run(repo: UserRepository) = CommandLineRunner {
        for (count in 1..100) {
            repo.save(UserEntity(UUID.randomUUID().toString()).apply {
                this.name = "User $count"
            })
        }
    }

}

fun main(args: Array<String>) {
    runApplication<SpringApiPaginationApplication>(*args)
}
