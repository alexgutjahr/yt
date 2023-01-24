package com.alexgutjahr

import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

data class User(val address: String, val nonce: String = UUID.randomUUID().toString())

@Service
class UserService {

    private val users = ConcurrentHashMap<String, User>()

    fun register(user: User) {
        users[user.address] = user
    }

    fun findByAddress(address: String) = users[address]

}
