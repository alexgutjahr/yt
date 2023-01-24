package com.alexgutjahr

import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val users: UserService, private val manager: AuthenticationManager) {

    @GetMapping("/challenge/{address}")
    fun challenge(@PathVariable address: String): String {
        users.findByAddress(address)?.let { return it.nonce }
        throw UnknownAddress(address)
    }

    @PostMapping("/auth")
    fun auth(@RequestBody request: SignRequest): Authentication? {
        return manager.authenticate(Web3Authentication(request.address, request.signature))
    }
}

data class SignRequest(val signature: String, val address: String)

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnknownAddress(address: String) : RuntimeException("Wallet address $address is not known")
