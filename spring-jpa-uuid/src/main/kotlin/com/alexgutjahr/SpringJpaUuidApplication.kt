package com.alexgutjahr

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringJpaUuidApplication {

    @Bean
    fun run(repo: UserRepository) = CommandLineRunner {
        for (count in 1..10) {
            repo.save(UserEntity().apply {
                this.name = "User $count"
            })
        }

        repo.findAll().forEach(::println)
    }

}

fun main(args: Array<String>) {
    runApplication<SpringJpaUuidApplication>(*args)
}
