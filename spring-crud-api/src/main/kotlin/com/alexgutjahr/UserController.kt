package com.alexgutjahr

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
@RequestMapping("/users")
class UserController(private val users: UserRepository) {

    @GetMapping
    fun all(): List<User> {
        return users.findAll().toList()
    }

    @GetMapping("/{email}")
    fun lookup(@PathVariable email: String): User {
        return users.findById(email).orElseThrow { UserNotFoundException(email) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): User {
        return users.save(user)
    }

    @PutMapping("/{email}")
    fun update(@PathVariable email: String, @RequestBody request: User): User {
        val user = users.findById(email).orElseThrow { UserNotFoundException(email) }

        return users.save(user.apply {
            this.name = request.name
        })
    }

    @DeleteMapping("/{email}")
    fun delete(@PathVariable email: String): ResponseEntity<HttpStatus> {
        if (users.existsById(email)) {
            users.deleteById(email)
        }

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(email: String) : RuntimeException("User $email not found.")
