package com.alexgutjahr

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import java.math.BigInteger

@SpringBootApplication
class SpringFlywayApplication {

    companion object {
        private val log = LoggerFactory.getLogger(SpringFlywayApplication::class.java)
    }

    @Bean
    fun run(jdbc: JdbcTemplate) = CommandLineRunner {
        val users = jdbc.query("SELECT * FROM users", DataClassRowMapper(User::class.java))
        log.info(users.toString())
    }

}

data class User(val id: BigInteger, val username: String)

fun main(args: Array<String>) {
    runApplication<SpringFlywayApplication>(*args)
}
