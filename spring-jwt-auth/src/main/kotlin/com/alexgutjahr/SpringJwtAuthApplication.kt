package com.alexgutjahr

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@SpringBootApplication
class SpringJwtAuthApplication

fun main(args: Array<String>) {
    runApplication<SpringJwtAuthApplication>(*args)
}

@RestController
class UserController(
  private val jwtSupport: JwtSupport,
  private val encoder: PasswordEncoder,
  private val users: ReactiveUserDetailsService) {

  @PostMapping("/login")
  suspend fun login(@RequestBody login: Login): Jwt {
    val user = users.findByUsername(login.username).awaitSingleOrNull()

    user?.let {
      if (encoder.matches(login.password, it.password)) {
        return Jwt(jwtSupport.generate(it.username).value)
      }
    }

    throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
  }

  @GetMapping("/me")
  suspend fun me(@AuthenticationPrincipal principal: Principal): Profile {
    return Profile(principal.name)
  }


}

data class Login(val username: String, val password: String)
data class Jwt(val token: String)

data class Profile(val username: String)
