package com.alexgutjahr

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringWalletApplication {

  @Bean
  fun run(users: UserService) = CommandLineRunner {
    users.register(User("0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266"))
  }
}

fun main(args: Array<String>) {
  runApplication<SpringWalletApplication>(*args)
}
